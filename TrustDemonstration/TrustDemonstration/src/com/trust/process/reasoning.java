package com.trust.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public final class reasoning implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void reasoner(String data_out, String data_in, String ruleFile, String mode){
		
		Model model = ModelFactory.createDefaultModel();
		Resource configuration = model.createResource();
		configuration.addProperty(ReasonerVocabulary.PROPruleMode, mode);
		configuration.addProperty(ReasonerVocabulary.PROPruleSet, ruleFile);
		Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(
				configuration);

		Model modelRDF = FileManager.get().loadModel(data_in);

		InfModel infModel = ModelFactory.createInfModel(reasoner, modelRDF);
		infModel.prepare();

		if (infModel != null) {
			try {
				// output inferences to file
				File outFile = new File(data_out);
				Writer writer = new FileWriter(outFile);
				infModel.write(writer, "TURTLE");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Exception caught" + e.getMessage());
			}
		}	
	}
		
}
