import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


// Class DelivA does the work for deliverable DelivA of the Prog340

public class DelivA {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	
	public DelivA( File in, Graph gr ) {
		inputFile = in;
		g = gr;
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		
		try {
			output = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		System.out.println("The graph has some nodes in it");
		System.out.println("Number of nodes: "+ g.getNodeList().size());

		ArrayList<Node> allNodes = g.getNodeList();
		for(Node node: allNodes){
			System.out.println("name: "+ node.getName() + " abbrv: "+ node.getAbbrev() + " value: "+ node.getVal());
		}

		Collections.sort(allNodes, new Comparator<Node>(){
			@Override
			public int compare(Node node1, Node node2){
				int valueInt1 = Integer.parseInt(node1.getVal());
				int valueInt2 = Integer.parseInt(node2.getVal());

				return Integer.compare(valueInt1, valueInt2);
			}
		});

		StringBuilder pathBuilder = new StringBuilder("Path ");

		for (int i = 0; i < allNodes.size(); i++) {
			pathBuilder.append(allNodes.get(i).getAbbrev());
			pathBuilder.append(" ");
		}
		System.out.println(pathBuilder);
		

		output.flush();
	}

}
