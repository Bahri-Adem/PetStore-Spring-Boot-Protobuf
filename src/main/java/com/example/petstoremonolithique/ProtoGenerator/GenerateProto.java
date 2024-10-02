package com.example.petstoremonolithique.ProtoGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import fr.bl.PojoToProto.JavaToProto;

public class GenerateProto {

	public static void main(String[] args) {
		List<Class<?>> entityClasses = findEntityClasses("com.example.petstoremonolithique");
		generateProtoFile(entityClasses);
	}

	private static List<Class<?>> findEntityClasses(String packageName) {
		List<Class<?>> entityClasses = new ArrayList<>();
		try {
			// Scan the specified package for classes
			ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
					false);
			provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

			for (BeanDefinition beanDef : provider.findCandidateComponents(packageName)) {
				entityClasses.add(Class.forName(beanDef.getBeanClassName()));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return entityClasses;
	}

	private static void generateProtoFile(List<Class<?>> classes) {
		// Create a StringBuilder to aggregate the protobuf definitions
		StringBuilder protoContentBuilder = new StringBuilder();

		// Append the common syntax and options
		protoContentBuilder.append("syntax = \"proto3\";\n\n");
		protoContentBuilder.append("option java_package = \"com.example.petstoremonolithique.Entities\";\n");
		protoContentBuilder.append("option java_outer_classname = \"PetStore\";\n");
		protoContentBuilder.append("option optimize_for = SPEED;\n\n");
		protoContentBuilder.append("import \"google/protobuf/timestamp.proto\";\n");
		protoContentBuilder.append("import \"protogen/options.proto\";\n");
		protoContentBuilder.append("option (protogen.enable) = true;\n\n");

		// Generate and append protobuf definitions for each class
		for (Class<?> clazz : classes) {
			JavaToProto javaToProto = new JavaToProto(clazz);
			protoContentBuilder.append(javaToProto.toString());
		}

		// create dir folder
		String folderPath = "src/main/proto/";
		File folder = new File(folderPath);
		boolean success = folder.mkdir();

		// Write proto content to a file
		String protoFileName = "PetStore.proto";

		File protoFile = new File(folderPath + protoFileName);

		try (FileWriter writer = new FileWriter(protoFile)) {
			writer.write(protoContentBuilder.toString());
			System.out.println(protoFileName + " has been successfully created at src/main/proto.");
		} catch (IOException e) {
			System.err.println("An error occurred while writing the .proto file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
