package ru.bmstu.iu9;

import akka.actor.AbstractActor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ActorRunTest extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }

    private static String evalScript(String jscript, String functionName, String params) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params)
    }
}
