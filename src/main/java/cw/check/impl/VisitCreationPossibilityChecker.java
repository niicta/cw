package cw.check.impl;

import cw.check.AbstractChecker;
import cw.check.Checker;
import cw.check.CheckerConstants;
import cw.utils.context.ContextMap;

import javax.ejb.EJB;
import java.util.Collection;
import java.util.LinkedList;

public class VisitCreationPossibilityChecker extends AbstractChecker {
    @EJB(beanName = "visitWeekendChecker")
    private Checker visitWeekendChecker;
    private Collection<Checker> checkers;

    @Override
    public ContextMap check(ContextMap contextMap) {
        if (checkers == null){
            checkers = new LinkedList<>();
            checkers.add(visitWeekendChecker);
        }
        return isPossibleToCreateVisit(contextMap);
    }

    private ContextMap isPossibleToCreateVisit(ContextMap contextMap) {
        for (Checker checker : checkers){
            ContextMap checkerResult = checker.check(contextMap);
            if (checkerResult.getValue(CheckerConstants.CHECKER_RESULT_ATTRIBUTE)
                    .equals(CheckerConstants.CHECKER_RESULT_FAIL_VALUE)){
                return checkerResult;
            }
        }
        return buildSuccessResult();
    }
}
