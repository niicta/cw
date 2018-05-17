package cw.model.operations;

import cw.data.DAO;
import cw.model.Space;
import cw.model.Visit;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

@Stateless
public class SpaceByFixedVisitsComparator implements Comparator<Space>
{
    @EJB(beanName = "visitDao")
    private DAO<Visit> visitDao;
    private Collection<Visit> visits;
    private Map<Space, Boolean> spacesStatuses;

    @Override
    public int compare(Space o1, Space o2){
        if (visits == null){
            visits = visitDao.findAll();
        }
        if (spacesStatuses.containsKey(o1)){

        }
    }
}
