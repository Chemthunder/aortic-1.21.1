package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;

/**
 * @author Chemthunder
 */
public interface AorticComponentTypes {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Aortic.MOD_ID);

    static void init() {}
}
