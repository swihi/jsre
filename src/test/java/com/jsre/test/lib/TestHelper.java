package com.jsre.test.lib;

import com.jsre.RuleEngine;
import com.jsre.TypedRuleEngine;
import com.jsre.builder.RuleEngineFactory;
import com.jsre.configuration.Document;
import com.jsre.test.actions.LogAction;
import com.jsre.test.configuration.model.ComputerDocument;
import com.jsre.test.configuration.model.TestDocument;


public class TestHelper {

	public static RuleEngine buildEngineWithoutDoc(String configFile) {
		String json = ResourceFileHelper.getFileContentNoException(configFile);
		RuleEngine engine = RuleEngineFactory.getEngine(json);
		engine.registerAction("LogAction", new LogAction());
		return engine;
	}

	public static TypedRuleEngine<TestDocument> buildTestDocEngine(String configFile) {
		String json = ResourceFileHelper.getFileContentNoException(configFile);
		TypedRuleEngine<TestDocument> engine = RuleEngineFactory.getTypedEngine(json, TestDocument.class);
		engine.registerAction("LogAction", new LogAction());
		return engine;
	}

	public static <TDoc extends Document> TypedRuleEngine<TDoc> buildDocEngine(String configFile, Class<TDoc> docType) {
		String json = ResourceFileHelper.getFileContentNoException(configFile);
		TypedRuleEngine<TDoc> engine = RuleEngineFactory.getTypedEngine(json, docType);
		engine.registerAction("LogAction", new LogAction());
		return engine;
	}

	public static TypedRuleEngine<ComputerDocument> buildComputerDocEngine(String configFile) {
		String json = ResourceFileHelper.getFileContentNoException(configFile);
		TypedRuleEngine<ComputerDocument> engine = RuleEngineFactory.getTypedEngine(json, ComputerDocument.class);
		engine.registerAction("LogAction", new LogAction());
		return engine;
	}
}
