package cz.zajezdy.data.bengine.engine.impl;

import cz.zajezdy.data.bengine.action.Action;
import cz.zajezdy.data.bengine.configuration.Configuration;
import cz.zajezdy.data.bengine.configuration.Rule;

import java.util.HashMap;
import java.util.List;

public class BasicScriptBuilder extends AbstractScriptBuilder {
    public static String getScript(Configuration configuration, HashMap<String, Action> registeredActions) {
        StringBuilder script = new StringBuilder();

        // script += "var execAction = function(actionMap, actionName, param) {
        // com.jsre.engine.impl.ActionExecutor.exec(actionMap, actionName);
        // actionMap.get(actionName).execute(); };";

        script.append("" +
                "var executeScript = function(inputJson, registeredActions) { \n" +
                "var input; try { input = JSON.parse(inputJson); } catch (e) { return inputJson; }\n");

        script.append("var document = ").append(configuration.getDocument()).append(";\n");

        @SuppressWarnings("unchecked")
        List<String> preExecution = configuration.getPreExecution();
        script.append(getPreExecutionScriptPart(preExecution));

        @SuppressWarnings("unchecked")
        List<? extends Rule> rules = configuration.getRules();
        script.append(getRulesScriptPart(rules, registeredActions));

        @SuppressWarnings("unchecked")
        List<String> postExecution = configuration.getPostExecution();
        script.append(getPostExecutionScriptPart(postExecution));

        script.append("return JSON.stringify(document);\n}\n");
        return script.toString();
    }
}
