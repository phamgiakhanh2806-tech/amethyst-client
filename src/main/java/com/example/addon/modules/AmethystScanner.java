package com.example.addon.modules;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.block.Blocks;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public class AmethystScanner extends Module {
    private final Set<BlockPos> detectedBlocks = new HashSet<>();

    public AmethystScanner(Category category) {
        super(category, "amethyst-scanner", "Quét Amethyst kèm báo âm thanh.");
    }

    @Override
    public void onActivate() {
        detectedBlocks.clear();
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.player == null || mc.world == null) return;

        BlockPos playerPos = mc.player.getBlockPos();
        int radius = 16; 

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos targetPos = playerPos.add(x, y, z);
                    if (detectedBlocks.contains(targetPos)) continue;

                    var block = mc.world.getBlockState(targetPos).getBlock();
                    if (block == Blocks.BUDDING_AMETHYST || block == Blocks.AMETHYST_CLUSTER) {
                        detectedBlocks.add(targetPos);
                        ChatUtils.info("Có Amethyst tại X: " + targetPos.getX() + " Y: " + targetPos.getY() + " Z: " + targetPos.getZ());
                        mc.world.playSound(mc.player, targetPos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    }
                }
            }
        }
    }
}

