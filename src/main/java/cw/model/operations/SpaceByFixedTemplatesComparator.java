package cw.model.operations;

import cw.data.DAO;
import cw.model.Space;
import cw.model.Visit;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Stateless(name = "spaceByFixedTemplatesComparator", mappedName = "spaceByFixedTemplatesComparator")
@Local(Comparator.class)
public class SpaceByFixedTemplatesComparator implements Comparator<Space>
{
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    private Collection<Visit> visits;
    private Map<Space, Boolean> spacesStatuses;

    @Override
    public int compare(Space o1, Space o2){
        init();
        boolean firstHasFixedVisit = checkIfSpaceHasFixedVisit(o1);
        boolean secondHasFixedVisit = checkIfSpaceHasFixedVisit(o2);
        if (firstHasFixedVisit == secondHasFixedVisit){
            return o1.getId() - o2.getId();
        }
        else if (firstHasFixedVisit){
            if (!secondHasFixedVisit){
                return 1;
            }
        }
        return -1;
    }

    private void init(){
        if (visits == null){
            visits = visitDao.findAll();
        }
        if (spacesStatuses == null){
            spacesStatuses = new HashMap<>();
        }
    }

    private boolean checkIfSpaceHasFixedVisit(Space space){
        if (spacesStatuses.containsKey(space)){
            return spacesStatuses.get(space);
        }
        else {
            for (Visit visit : visits){
                if (visit.getSpace().getId() == space.getId()
                        && visit.getOrder().getTemplate().isFixed()
                        && !spacesStatuses.containsKey(visit.getSpace())){
                    spacesStatuses.put(space, true);
                    return true;
                }
            }
            if (!spacesStatuses.containsKey(space)){
                spacesStatuses.put(space, false);
                return false;
            }
        }
        return false;
    }
}
