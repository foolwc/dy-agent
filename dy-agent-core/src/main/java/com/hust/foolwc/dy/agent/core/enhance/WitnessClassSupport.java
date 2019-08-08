package com.hust.foolwc.dy.agent.core.enhance;

import net.bytebuddy.pool.TypePool;

public abstract class WitnessClassSupport {

    private static final TypePool TYPE_POOL = TypePool.Default.ofClassPath();

    /**
     * Check witness classes' existence.
     *
     * @return true if all given witness classes exist.
     */
    public boolean witness() {
        String[] witnessClasses = witnessClasses();
        if (witnessClasses != null) {
            for (String witnessClass : witnessClasses) {
                if (!TYPE_POOL.describe(witnessClass).isResolved()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Give the witness classes needed to check.
     *
     * @return witness classes needed to check.
     */
    public String[] witnessClasses() {
        return new String[]{};
    }
}
