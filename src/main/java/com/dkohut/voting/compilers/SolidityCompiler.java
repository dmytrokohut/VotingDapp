package com.dkohut.voting.compilers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class SolidityCompiler {

	private static String pathToFiles;
	private static String pathForOutput;
	
	private static final Logger logger = Logger.getLogger(SolidityCompiler.class.getName());
	
	public static void main(String[] args) {
		
		if(args.length >= 2) {
			pathToFiles = args[0].toString();
			pathForOutput = args[1].toString();
			
			File directory = new File(pathToFiles);
			if(directory.exists() && directory.isDirectory()) {
				Arrays.stream(directory.list((file, name) -> {
					return name.endsWith(".sol");
				}))
				.forEach(SolidityCompiler::compileSolidity);
			}
			
			logger.info("Contract compiled");
			
		} else {
			throw new IllegalArgumentException("Incorrect number of arguments, you should give 2 arguments");
		}
	}
	
	public static void compileSolidity(String fileName) {
		ProcessBuilder compilerProcess = new ProcessBuilder(
				"solc",
				pathToFiles + "/" + fileName,
				"--overwrite",
				"--bin",
				"--abi",
				"--optimize",
				"-o",
				pathForOutput
				);
		
		try {
			Process solc = compilerProcess.start();
			solc.waitFor();
		} catch(IOException | InterruptedException e) {
			logger.info("Exception during process execution\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
	}
}
