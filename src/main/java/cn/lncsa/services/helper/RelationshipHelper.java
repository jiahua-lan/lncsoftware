package cn.lncsa.services.helper;

import cn.lncsa.common.ListTools;
import cn.lncsa.data.dao.domain.IRelationshipRepository;
import cn.lncsa.data.model.domain.IRelationMaster;
import cn.lncsa.data.model.domain.IRelationSlave;
import cn.lncsa.data.model.domain.IRelationship;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cattenlinger on 2016/9/29.
 *
 * RelationshipHelper is for handling relationship modifying.
 *
 * Because the code in updateRelationship () is repeated in the Service layer,
 * I gener- alize it and implement the master-slave relationship model to determine the difference in relation lists.
 *
 * @see IRelationshipRepository
 */
public class RelationshipHelper<M extends IRelationMaster,S extends IRelationSlave,R extends IRelationship<M,S>> {

    //Get master class type
    private Class<IRelationMaster> masterClass = IRelationMaster.class;
    //Get slave class type
    private Class<IRelationSlave> slaveClass = IRelationSlave.class;
    //Get relation class type
    private Class<IRelationship> relationClass = IRelationship.class;

    /**
     * This method updates the relationships between objects in the database by adding or removing lists.
     *
     * @param master
     * @param slaveListDiff
     * @param relationshipRepository
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void updateRelationship(M master, List<S>[] slaveListDiff, IRelationshipRepository<M,S,R> relationshipRepository) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(master == null) return;
        List<R> delList = relationshipRepository.getRelationships(master, slaveListDiff[ListTools.LIST_DELETE]);
        List<R> addList = new LinkedList<>();
        for (S slave : slaveListDiff[ListTools.LIST_ADD]) addList.add(slaveFactory(master,slave));
        relationshipRepository.delete(delList);
        relationshipRepository.save(addList);
    }

    @SuppressWarnings("unchecked")
    private R slaveFactory(M master, S slave) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        IRelationship<M,S> relationship = (IRelationship<M, S>) relationClass.newInstance();
        Method setRelationship = relationClass.getMethod("setRelationship",masterClass,slaveClass);
        setRelationship.invoke(relationship,master,slave);
        return (R) relationship;
    }

}
