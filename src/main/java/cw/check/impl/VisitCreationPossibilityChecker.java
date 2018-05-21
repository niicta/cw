package cw.check.impl;

import cw.check.AbstractChecker;
import cw.check.Checker;
import cw.check.CheckerConstants;
import cw.utils.context.ContextMap;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.LinkedList;

@Stateless(name = "visitCreationPossibilityChecker", mappedName = "visitCreationPossibilityChecker")
public class VisitCreationPossibilityChecker extends AbstractChecker {
    private static final Logger LOG = Logger.getLogger(VisitCreationPossibilityChecker.class);

    @EJB(beanName = "visitWeekendChecker")
    private Checker visitWeekendChecker;
    @EJB(beanName = "visitTimeEnableChecker")
    private Checker visitTimeEnableChecker;
    private Collection<Checker> checkers;

    @Override
    public ContextMap check(ContextMap contextMap) {
        debug("visit creation possibility checker started");
        if (checkers == null){
            checkers = new LinkedList<>();
            checkers.add(visitWeekendChecker);
            checkers.add(visitTimeEnableChecker);
        }
        return isPossibleToCreateVisit(contextMap);
    }

    private ContextMap isPossibleToCreateVisit(ContextMap contextMap) {
        ContextMap resultMap = new ContextMap();
        for (Checker checker : checkers){
            debug("current checker - " + checker.getClass());
            ContextMap checkerResult = checker.check(contextMap);
            if (checkerResult.getValue(CheckerConstants.CHECKER_RESULT_ATTRIBUTE)
                    .equals(CheckerConstants.CHECKER_RESULT_FAIL_VALUE)){
                debug("failed");
                return checkerResult;
            }
            for (String attribute : checkerResult.getAttributes()){
                resultMap.put(attribute, checkerResult.getValue(attribute));
            }
            debug("success");
        }
        debug("it is possible to create visit");
        return resultMap;
    }

    private void debug(String s){
        LOG.debug(s);
    }
}
