package ru.bmstu.iu9;

import akka.actor.AbstractActor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class ActorRunTest extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }

    private static String evalScript(String jscript, String functionName, ArrayList<Object> params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params).toString();
    }

    private String runTestMessage(RunTestMessage test) {
        String response;
        try {
            String result = evalScript(test.getJsScript(), test.getFunctionName(), test.getParams())
        } catch (ScriptException | NoSuchMethodException err) {
            response = ""
        }
    }
}
