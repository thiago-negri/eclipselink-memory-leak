package com.produce;

import java.util.UUID;
import java.util.Vector;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.Session;

public class EclipseLinkUUIDGenerator extends Sequence implements SessionCustomizer {

    private static final long serialVersionUID = 1L;

    public EclipseLinkUUIDGenerator() {
        super();
    }

    public EclipseLinkUUIDGenerator(String name) {
        super(name);
    }

    @Override
    public Object getGeneratedValue(Accessor accessor, AbstractSession writeSession, String seqName) {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Vector getGeneratedVector(Accessor accessor, AbstractSession writeSession, String seqName, int size) {
        return new Vector();
    }

    @Override
    public void onConnect() {
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public boolean shouldAcquireValueAfterInsert() {
        return false;
    }

    public boolean shouldOverrideExistingValue(String seqName, Object existingValue) {
        return ((String) existingValue).isEmpty();
    }

    @Override
    public boolean shouldUseTransaction() {
        return false;
    }

    @Override
    public boolean shouldUsePreallocation() {
        return false;
    }

    public void customize(Session session) throws Exception {
        EclipseLinkUUIDGenerator sequence = new EclipseLinkUUIDGenerator("system-uuid");
        session.getLogin().addSequence(sequence);
    }
}
