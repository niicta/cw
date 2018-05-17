package cw.check;

import cw.utils.context.ContextMap;

public abstract class AbstractChecker implements Checker {
    protected ContextMap buildFailResult(String reason){
        ContextMap result = new ContextMap();
        result.put(CheckerConstants.CHECKER_RESULT_ATTRIBUTE, CheckerConstants.CHECKER_RESULT_FAIL_VALUE);
        result.put(CheckerConstants.CHECKER_FAIL_REASON, reason);
        return result;
    }
    protected ContextMap buildSuccessResult(){
        ContextMap result = new ContextMap();
        result.put(CheckerConstants.CHECKER_RESULT_ATTRIBUTE, CheckerConstants.CHECKER_RESULT_SUCCESS_VALUE);
        return result;
    }
}
