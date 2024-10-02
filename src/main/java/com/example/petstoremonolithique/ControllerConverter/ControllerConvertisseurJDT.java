package com.example.petstoremonolithique.ControllerConverter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

public class ControllerConvertisseurJDT {

	public static void main(String[] args) {
		String sourceDirectory = "src/main/java/com/example/petstoremonolithique/Controllers";
		String protoPackage = "package com.example.petstoremonolithique.ProtoControllers;";

		String protoDirectoryPath = sourceDirectory + "/../ProtoControllers";
		File protoDirectory = new File(protoDirectoryPath);
		createProtoControllersDirectory(protoDirectory);

		File directory = new File(sourceDirectory);
		File[] files = directory.listFiles((dir, name) -> name.endsWith(".java"));

		if (files != null) {
			for (File file : files) {
				convertToProtoController(file, protoPackage, protoDirectory);
			}
		}
	}

	private static void createProtoControllersDirectory(File protoDirectory) {
		if (!protoDirectory.exists()) {
			if (protoDirectory.mkdirs()) {
				System.out.println("ProtoControllers package directory created.");
			} else {
				System.err.println("Failed to create ProtoControllers package directory.");
			}
		}
	}

	private static void convertToProtoController(File file, String protoPackage, File protoDirectory) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			StringBuilder content = new StringBuilder();
			String line;
			boolean isRestController = false;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
				if (line.contains("@RestController")) {
					isRestController = true;
				}
			}
			if (isRestController) {
				String modifiedContent = modifyContent(content.toString(), protoPackage);
				modifiedContent = renameClassToProto(modifiedContent, file.getName());
				saveModifiedContent(modifiedContent, file, protoDirectory);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void saveModifiedContent(String modifiedContent, File file, File protoDirectory) {
		String protoFileName = file.getName().replace(".java", "Proto.java");
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(Objects.requireNonNull(protoDirectory) + "/" + protoFileName))) {
			writer.write(modifiedContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String modifyContent(String content, String protoPackage) {
		content = content.replace("package com.example.petstoremonolithique.Controllers;", protoPackage);
		content = addImportStatement(content);
		content = content.replace("@RestController", "@RestController\n@RequestMapping(\"/proto\")");
		content = modifyPostMethodMappings(content);
		content = modifyPutMappings(content);
		content = modifyGetMappings(content);
		content = modifyGetByIdMappings(content);
		return content;
	}

	private static boolean hasMappingAnnotation(MethodDeclaration methodDeclaration, String annotationName) {
		for (Object modifier : methodDeclaration.modifiers()) {
			if (modifier instanceof Annotation) {
				Annotation annotation = (Annotation) modifier;
				if (annotation.getTypeName().toString().endsWith(annotationName)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isPostMapping(MethodDeclaration methodDeclaration) {
		return hasMappingAnnotation(methodDeclaration, "PostMapping");
	}

	private static boolean isPutMapping(MethodDeclaration methodDeclaration) {
		return hasMappingAnnotation(methodDeclaration, "PutMapping");
	}

	private static boolean isGetAllMapping(MethodDeclaration methodDeclaration) {
		return hasMappingAnnotation(methodDeclaration, "GetMapping") && !methodDeclaration.toString().contains("{id}");
	}

	private static boolean isGetByIdMapping(MethodDeclaration methodDeclaration) {
		return hasMappingAnnotation(methodDeclaration, "GetMapping") && methodDeclaration.toString().contains("{id}");
	}

	private static String modifyMethodMappings(String content, Predicate<MethodDeclaration> mappingPredicate,
			BiFunction<MethodDeclaration, ASTRewrite, MethodDeclaration> modifyFunction) {
		CompilationUnit astRoot = parse(content);
		final ASTRewrite rewriter = ASTRewrite.create(astRoot.getAST());
		StringBuilder modifiedContent = new StringBuilder(content);
		int[] insertIndex = { -1 };
		MethodDeclaration[] modifiedMethod = { null };

		astRoot.accept(new ASTVisitor() {
			@Override
			public boolean visit(MethodDeclaration methodDeclaration) {
				if (mappingPredicate.test(methodDeclaration) && insertIndex[0] == -1) {
					System.out.println("Old Method Declaration \n" + methodDeclaration);
					modifiedMethod[0] = modifyFunction.apply(methodDeclaration, rewriter);
					System.out.println("New Method Declaration \n" + modifiedMethod[0]);
					int startPosition = methodDeclaration.getStartPosition();
					int endPosition = startPosition + methodDeclaration.getLength();
					modifiedContent.delete(startPosition, endPosition);
					int lineNumber = astRoot.getLineNumber(startPosition);
					int columnNumber = astRoot.getColumnNumber(startPosition);
					insertIndex[0] = astRoot.getPosition(lineNumber, columnNumber);
				}
				return super.visit(methodDeclaration);
			}
		});
		if (insertIndex[0] != -1 && modifiedMethod[0] != null) {
			String modifiedMethodString = modifiedMethod[0].toString();
			if (!modifiedContent.toString().contains(modifiedMethodString)) {
				modifiedContent.insert(insertIndex[0], "\n");
				modifiedContent.insert(insertIndex[0], modifiedMethodString);
			}
		}

		return modifiedContent.toString();
	}

	private static String modifyPostMethodMappings(String content) {
		return modifyMethodMappings(content, ControllerConvertisseurJDT::isPostMapping,
				ControllerConvertisseurJDT::modifyPostMappingMethod);
	}

	private static String modifyPutMappings(String content) {
		return modifyMethodMappings(content, ControllerConvertisseurJDT::isPutMapping,
				ControllerConvertisseurJDT::modifyPutMappingMethod);
	}

	private static String modifyGetMappings(String content) {
		return modifyMethodMappings(content, ControllerConvertisseurJDT::isGetAllMapping,
				ControllerConvertisseurJDT::modifyGetMappingMethod);
	}

	private static String modifyGetByIdMappings(String content) {
		return modifyMethodMappings(content, ControllerConvertisseurJDT::isGetByIdMapping,
				ControllerConvertisseurJDT::modifyGetByIdMappingMethod);
	}

	private static CompilationUnit parse(String content) {
		ASTParser parser = ASTParser.newParser(AST.JLS10);
		parser.setSource(content.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		return (CompilationUnit) parser.createAST(null);
	}

	private static MethodDeclaration modifyPostMappingMethod(MethodDeclaration methodDeclaration, ASTRewrite rewriter) {
		return modifyMappingMethod(methodDeclaration, rewriter, false);
	}

	private static MethodDeclaration modifyPutMappingMethod(MethodDeclaration methodDeclaration, ASTRewrite rewriter) {
		return modifyMappingMethod(methodDeclaration, rewriter, true);
	}

	private static MethodDeclaration modifyMappingMethod(MethodDeclaration methodDeclaration, ASTRewrite rewriter,
			boolean includeIdParam) {
		String methodName = methodDeclaration.getName().getIdentifier();
		String paramName = getRequestBodyParamName(methodDeclaration);
		if (paramName == null) {
			return null;
		}
		String entityType = methodDeclaration.getReturnType2().toString();
		AST ast = methodDeclaration.getAST();

		MethodDeclaration newMethod = createNewMethod(ast, methodDeclaration, methodName, entityType, paramName,
				includeIdParam, rewriter);

		replaceMethodInTypeDeclaration(methodDeclaration, newMethod, rewriter);
		return newMethod;
	}

	private static String getRequestBodyParamName(MethodDeclaration methodDeclaration) {
		for (SingleVariableDeclaration parameter : (List<SingleVariableDeclaration>) methodDeclaration.parameters()) {
			for (Object modifier : parameter.modifiers()) {
				if (modifier instanceof Annotation) {
					Annotation annot = (Annotation) modifier;
					if (annot.getTypeName().toString().equals("RequestBody")) {
						return parameter.getName().getIdentifier();
					}
				}
			}
		}
		return null;
	}

	private static MethodDeclaration createNewMethod(AST ast, MethodDeclaration originalMethod, String methodName,
			String entityType, String paramName, boolean includeIdParam, ASTRewrite rewriter) {
		MethodDeclaration newMethod = ast.newMethodDeclaration();
		newMethod.modifiers().add(addMappingAnnotation(originalMethod, ast, rewriter));
		newMethod.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
		newMethod.setName(ast.newSimpleName(methodName));
		newMethod.setReturnType2(ast.newSimpleType(ast.newSimpleName("Grpc" + entityType)));

		for (SingleVariableDeclaration parameter : (List<SingleVariableDeclaration>) originalMethod.parameters()) {
			SingleVariableDeclaration newParameter = (SingleVariableDeclaration) ASTNode.copySubtree(ast, parameter);
			if (newParameter.getType().toString().equals(entityType)) {
				newParameter.setType(ast.newSimpleType(ast.newSimpleName("Grpc" + entityType)));
			}
			newMethod.parameters().add(newParameter);
		}

		Block methodBody = createMethodBody(ast, originalMethod, methodName, entityType, paramName, includeIdParam);
		newMethod.setBody(methodBody);

		return newMethod;
	}

	private static Block createMethodBody(AST ast, MethodDeclaration originalMethod, String methodName,
			String entityType, String paramName, boolean includeIdParam) {
		Block methodBody = ast.newBlock();

		MethodInvocation invocation = ast.newMethodInvocation();
		invocation.setExpression(ast.newThisExpression());
		invocation.setName(ast.newSimpleName(methodName));

		MethodInvocation fromGrpcInvocation = ast.newMethodInvocation();
		fromGrpcInvocation.setExpression(ast.newSimpleName(paramName + "Service"));
		fromGrpcInvocation.setName(ast.newSimpleName(methodName));

		MethodInvocation fromGrpcMethodInvocation = ast.newMethodInvocation();
		fromGrpcMethodInvocation.setExpression(ast.newSimpleName(entityType));
		fromGrpcMethodInvocation.setName(ast.newSimpleName("fromGrpc"));

		if (includeIdParam) {
			fromGrpcInvocation.arguments().add(ast.newSimpleName("id"));
		}

		fromGrpcMethodInvocation.arguments().add(ast.newSimpleName(paramName));
		fromGrpcInvocation.arguments().add(fromGrpcMethodInvocation);

		MethodInvocation toGrpcInvocation = ast.newMethodInvocation();
		toGrpcInvocation.setExpression(fromGrpcInvocation);
		toGrpcInvocation.setName(ast.newSimpleName("toGrpc"));

		ReturnStatement returnStmt = ast.newReturnStatement();
		returnStmt.setExpression(toGrpcInvocation);

		List<Statement> originalStatements = (List<Statement>) originalMethod.getBody().statements();
		for (int i = 0; i < originalStatements.size() - 1; i++) {
			methodBody.statements().add(ASTNode.copySubtree(ast, originalStatements.get(i)));
		}
		methodBody.statements().add(returnStmt);

		return methodBody;
	}

	private static MethodDeclaration modifyGetMappingMethod(MethodDeclaration methodDeclaration, ASTRewrite rewriter) {
		String methodName = methodDeclaration.getName().getIdentifier();
		AST ast = methodDeclaration.getAST();
		MethodDeclaration newMethod = ast.newMethodDeclaration();
		String entityType;
		Type returnType = methodDeclaration.getReturnType2();
		if (returnType.isParameterizedType()) {
			ParameterizedType parameterizedType = (ParameterizedType) returnType;
			Type typeArgument = (Type) parameterizedType.typeArguments().get(0);
			entityType = typeArgument.toString();
		} else {
			entityType = returnType.toString();
		}
		String lowerEntityType = Character.toLowerCase(entityType.charAt(0)) + entityType.substring(1);
		// Create the method declaration
		newMethod.modifiers().add(addMappingAnnotation(methodDeclaration, ast, rewriter));
		newMethod.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD)); // Add public modifier
		newMethod.setName(ast.newSimpleName(methodName));
		newMethod.setReturnType2(ast.newSimpleType(ast.newSimpleName("Grpc" + entityType + "s")));
		// Create the method body
		Block methodBody = ast.newBlock();
		// Create the variable declaration for orderList
		VariableDeclarationFragment orderListFragment = ast.newVariableDeclarationFragment();
		orderListFragment.setName(ast.newSimpleName(lowerEntityType + "List"));
		ClassInstanceCreation orderListCreation = ast.newClassInstanceCreation();
		ParameterizedType listType = ast.newParameterizedType(ast.newSimpleType(ast.newSimpleName("ArrayList")));
		listType.typeArguments().add(ast.newSimpleType(ast.newSimpleName("Grpc" + entityType))); // Add type argument
		orderListCreation.setType(listType);
		orderListFragment.setInitializer(orderListCreation);
		VariableDeclarationStatement orderListDeclaration = ast.newVariableDeclarationStatement(orderListFragment);
		orderListDeclaration.setType(ast.newSimpleType(ast.newSimpleName("List"))); // Set type to List
		methodBody.statements().add(orderListDeclaration);
		// Create the enhanced for loop for orders
		EnhancedForStatement forEachStatement = ast.newEnhancedForStatement();
		SingleVariableDeclaration orderVariable = ast.newSingleVariableDeclaration();
		orderVariable.setType(ast.newSimpleType(ast.newSimpleName(entityType)));
		orderVariable.setName(ast.newSimpleName(lowerEntityType));
		forEachStatement.setParameter(orderVariable);
		MethodInvocation getOrdersInvocation = ast.newMethodInvocation();
		getOrdersInvocation.setExpression(ast.newSimpleName(lowerEntityType + "Service"));
		getOrdersInvocation.setName(ast.newSimpleName("get" + entityType + "s"));
		forEachStatement.setExpression(getOrdersInvocation);
		Block loopBody = ast.newBlock();
		// Create the method call to convert order to GrpcOrder
		MethodInvocation toGrpcInvocation = ast.newMethodInvocation();
		toGrpcInvocation.setExpression(ast.newSimpleName(lowerEntityType));
		toGrpcInvocation.setName(ast.newSimpleName("toGrpc"));
		// Add the converted GrpcOrder to orderList
		MethodInvocation addInvocation = ast.newMethodInvocation();
		addInvocation.setExpression(ast.newSimpleName(lowerEntityType + "List"));
		addInvocation.setName(ast.newSimpleName("add"));
		addInvocation.arguments().add(toGrpcInvocation);
		ExpressionStatement addInvocationStatement = ast.newExpressionStatement(addInvocation);
		loopBody.statements().add(addInvocationStatement);
		forEachStatement.setBody(loopBody);
		methodBody.statements().add(forEachStatement);
		// Create the return statement
		MethodInvocation newBuilderInvocation = ast.newMethodInvocation();
		newBuilderInvocation.setExpression(ast.newSimpleName("Grpc" + entityType + "s"));
		newBuilderInvocation.setName(ast.newSimpleName("newBuilder"));
		MethodInvocation addAllGrpcOrderInvocation = ast.newMethodInvocation();
		addAllGrpcOrderInvocation.setExpression(newBuilderInvocation);
		addAllGrpcOrderInvocation.setName(ast.newSimpleName("addAllGrpc" + entityType));
		addAllGrpcOrderInvocation.arguments().add(ast.newSimpleName(lowerEntityType + "List"));
		MethodInvocation buildInvocation = ast.newMethodInvocation();
		buildInvocation.setExpression(addAllGrpcOrderInvocation);
		buildInvocation.setName(ast.newSimpleName("build"));
		ReturnStatement returnStatement = ast.newReturnStatement();
		returnStatement.setExpression(buildInvocation);
		methodBody.statements().add(returnStatement);
		newMethod.setBody(methodBody);
		replaceMethodInTypeDeclaration(methodDeclaration, newMethod, rewriter);
		return newMethod;
	}

	private static MethodDeclaration modifyGetByIdMappingMethod(MethodDeclaration methodDeclaration,
			ASTRewrite rewriter) {
		String methodName = methodDeclaration.getName().getIdentifier();
		String entityType = methodDeclaration.getReturnType2().toString(); // Create a new method with modified mappings
		AST ast = methodDeclaration.getAST();
		MethodDeclaration newMethod = ast.newMethodDeclaration();
		List<SingleVariableDeclaration> parameters = methodDeclaration.parameters();
		newMethod.modifiers().add(addMappingAnnotation(methodDeclaration, ast, rewriter));
		newMethod.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD)); // Add public modifier
		newMethod.setName(ast.newSimpleName(methodName));
		newMethod.setReturnType2(ast.newSimpleType(ast.newSimpleName("Grpc" + entityType)));
		for (SingleVariableDeclaration parameter : parameters) {
			SingleVariableDeclaration newParameter = (SingleVariableDeclaration) ASTNode.copySubtree(ast, parameter);
			if (newParameter.getType().toString().equals(entityType)) {
				newParameter.setType(ast.newSimpleType(ast.newSimpleName("Grpc" + entityType)));
			}
			newMethod.parameters().add(newParameter);
		}
		Block methodBody = ast.newBlock();
		String lowerEntityType = Character.toLowerCase(entityType.charAt(0)) + entityType.substring(1);
		// Get the existing return statement
		MethodInvocation getOrderInvocation = ast.newMethodInvocation();
		getOrderInvocation.setExpression(ast.newSimpleName(lowerEntityType + "Service"));
		getOrderInvocation.setName(ast.newSimpleName("get" + entityType));
		getOrderInvocation.arguments().add(ast.newSimpleName("id"));
		MethodInvocation toGrpcInvocation = ast.newMethodInvocation();
		toGrpcInvocation.setExpression(getOrderInvocation);
		toGrpcInvocation.setName(ast.newSimpleName("toGrpc"));
		// Create a new return statement with the method invocation
		ReturnStatement newReturnStmt = ast.newReturnStatement();
		newReturnStmt.setExpression(toGrpcInvocation);
		// Replace the old return statement with the new one
		methodBody.statements().add(newReturnStmt);
		newMethod.setBody(methodBody);
		replaceMethodInTypeDeclaration(methodDeclaration, newMethod, rewriter);
		return newMethod;
	}

	private static void replaceMethodInTypeDeclaration(MethodDeclaration oldMethod, MethodDeclaration newMethod,
			ASTRewrite rewriter) {
		ASTNode parent = oldMethod.getParent();
		if (parent instanceof TypeDeclaration) {
			TypeDeclaration typeDeclaration = (TypeDeclaration) parent;
			ListRewrite listRewrite = rewriter.getListRewrite(typeDeclaration,
					TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
			listRewrite.replace(oldMethod, newMethod, null);
		}
	}

	private static NormalAnnotation addMappingAnnotation(MethodDeclaration methodDeclaration, AST ast,
			ASTRewrite rewriter) {
		String annotationType = null;
		String annotationValue = null;
		List<?> modifiers = methodDeclaration.modifiers();
		for (Object modifier : modifiers) {
			if (modifier instanceof Annotation) {
				Annotation annotation = (Annotation) modifier;
				annotationType = annotation.getTypeName().getFullyQualifiedName();
				// Check the type of annotation
				if (annotation instanceof NormalAnnotation) {
					// For NormalAnnotation, get member-value pairs
					List<?> values = ((NormalAnnotation) annotation).values();
					for (Object value : values) {
						if (value instanceof MemberValuePair) {
							MemberValuePair pair = (MemberValuePair) value;
							if (pair.getName().getIdentifier().equals("value")) {
								Expression expression = pair.getValue();
								if (expression instanceof StringLiteral) {
									annotationValue = ((StringLiteral) expression).getLiteralValue();
								}
								break;
							}
						}
					}
				} else if (annotation instanceof SingleMemberAnnotation) {
					// For SingleMemberAnnotation, directly get the value
					Expression expression = ((SingleMemberAnnotation) annotation).getValue();
					if (expression instanceof StringLiteral) {
						annotationValue = ((StringLiteral) expression).getLiteralValue();
					}
				}

				break; // Stop after finding the first annotation
			}
		}
		// Create the annotation if it exists in the methodDeclaration
		if (annotationType != null) {
			NormalAnnotation putMappingAnnotation = ast.newNormalAnnotation();
			putMappingAnnotation.setTypeName(ast.newSimpleName(annotationType));
			// Assuming the annotation value is not null
			if (annotationValue != null) {
				putMappingAnnotation.values().add(createAnnotationValue(ast, annotationValue));
			}
			// Add the PutMapping annotation to the new method
			if (putMappingAnnotation != null) {
				ListRewrite modifiersRewrite = rewriter.getListRewrite(methodDeclaration,
						MethodDeclaration.MODIFIERS2_PROPERTY);
				modifiersRewrite.insertFirst(putMappingAnnotation, null);
			}
			return putMappingAnnotation;
		} else {
			return null;
		}

	}

	private static MemberValuePair createAnnotationValue(AST ast, String value) {
		MemberValuePair pair = ast.newMemberValuePair();
		pair.setName(ast.newSimpleName("value"));
		StringLiteral literal = ast.newStringLiteral();
		literal.setLiteralValue(value);
		pair.setValue(literal);
		return pair;
	}

	private static String addImportStatement(String content) {
		if (!content.contains("import org.springframework.web.bind.annotation.RequestMapping;")) {
			content = content.replace("import org.springframework.web.bind.annotation.RestController;",
					"import org.springframework.web.bind.annotation.RestController;\nimport org.springframework.web.bind.annotation.RequestMapping;");
		}
		if (!content.contains("import com.example.petstoremonolithique.Entities.PetStore.*;")) {
			content = content.replace("import org.springframework.web.bind.annotation.RestController;",
					"import org.springframework.web.bind.annotation.RestController;\nimport com.example.petstoremonolithique.Entities.PetStore.*;");
		}
		if (!content.contains("import java.util.ArrayList;")) {
			content = content.replace("import java.util.List;", "import java.util.List;\nimport java.util.ArrayList;");
		}
		return content;
	}

	private static String renameClassToProto(String content, String fileName) {
		String className = fileName.replace(".java", "");
		return content.replace("class " + className, "class " + className + "Proto");
	}
}