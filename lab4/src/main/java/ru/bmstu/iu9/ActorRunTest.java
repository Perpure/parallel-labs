package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class ActorRunTest extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RunTestMessage.class, this::runTestMessage)
                .build();
    }

    private static String evalScript(String jscript, String functionName, ArrayList<Object> params) throws ScriptException, NoSuchMethodException {
        System.out.println(jscript);
        System.out.println(functionName);
        System.out.println(params);
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params).toString();
    }

    private void runTestMessage(RunTestMessage test) {
        String response;
        try {
            String result = evalScript(test.getJsScript(), test.getFunctionName(), test.getParams());
            if (result.equals(test.getExpectedResult())) {
                response = String.format("OK %s", test.getTestName());
            } else {
                response = String.format("FAIL %s: returned %s, expected %s",
                        test.getTestName(), result, test.getExpectedResult());
            }
        } catch (ScriptException | NoSuchMethodException err) {
            response = String.format("Error during test %s run: %s", test.getTestName(), err);
        }
        System.out.println(response);
        sender().tell(new StoreMessage(test.getPackageId(), response), self());
    }
}
