package com.peak.aortic.core.index;

import com.peak.aortic.core.Aortic;
import com.peak.aortic.core.item.KnifeItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

/**
 * @author Chemthunder
 */
public interface AorticItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Aortic.MOD_ID);

    Item KNIFE = ITEMS.register("serrated_knife", KnifeItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(KnifeItem.createAttributes())
    );

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(AorticItems::buildItemGroupModifier);
    }

    private static void buildItemGroupModifier(FabricItemGroupEntries entries) {
        entries.add(KNIFE);
        entries.add(Item.fromBlock(AorticBlocks.HEMATOLOGY_TABLE));
    }
}
