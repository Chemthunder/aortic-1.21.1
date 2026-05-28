package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.component.HeldBloodComponentType;
import com.peak.aortic.core.item.KnifeItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface AorticItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Aortic.MOD_ID);

    Item KNIFE = ITEMS.register("knife", KnifeItem::new, new Item.Settings()
            .maxCount(1)
            .component(AorticComponentTypes.HELD_BLOOD, new HeldBloodComponentType(AorticBloodTypes.BASE, ""))
    );

    static void init() {}
}
