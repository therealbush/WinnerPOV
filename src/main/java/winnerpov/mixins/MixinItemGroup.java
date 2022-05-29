package winnerpov.mixins;

import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * {@code getIcon()} - changes category icons in creative inventory.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

@Mixin({ItemGroup.class}) public class MixinItemGroup
{
    @Shadow @Final private String id;

    @Inject(
            method = {"getIcon"},
            at = @At(
                    value = "RETURN"
            ),
            cancellable = true
    )

    public void getIcon(@NotNull CallbackInfoReturnable<ItemStack> cir)
    {
        cir.setReturnValue(new ItemStack(switch (this.id) {
            case "buildingBlocks" -> Items.OBSIDIAN;
            case "decorations" -> Items.ENCHANTING_TABLE;
            case "redstone" -> Items.TNT;
            case "transportation" -> Items.POWERED_RAIL;
            case "misc" -> Items.EXPERIENCE_BOTTLE;
            case "food" -> Items.ENCHANTED_GOLDEN_APPLE;
            case "tools" -> Items.NETHERITE_PICKAXE;
            case "combat" -> Items.END_CRYSTAL;
            case "brewing" -> PotionUtil.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.HEALING).getItem();

            case "hotbar" -> Items.WRITABLE_BOOK;
            case "inventory" -> Items.CHEST;

            default -> Items.COMMAND_BLOCK;
        }));
    }
}
