package com.ruby.throwingdisc.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ruby.throwingdisc.entity.DiscProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;

/**
 * Renders the flying disc flat, frisbee-style: the disc's plane faces the
 * heading of the throw, tilted by exactly the throw's pitch (the angle of
 * the thrower's view when it left the hand). Because the orientation is
 * derived from the current velocity every frame, a ricochet re-tilts the
 * disc to the reflected direction's incidence angle automatically.
 * The texture spins around the disc's normal while it flies.
 */
public class DiscRenderer extends EntityRenderer<DiscProjectileEntity> {
    private final ItemRenderer itemRenderer;

    public DiscRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DiscProjectileEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        Vec3 velocity = entity.getDeltaMovement();
        Vec3 f = velocity.lengthSqr() > 1.0e-6
                ? velocity.normalize()
                : entity.getLookAngle();
        double horizontal = Math.sqrt(f.x * f.x + f.z * f.z);

        // Pitch of the flight direction, i.e. the incidence angle the
        // thrower aimed with. The flat disc is tilted by this same angle.
        float pitchDeg = (float) Math.toDegrees(Math.asin(Mth.clamp(f.y, -1.0, 1.0)));
        // Heading of the flight direction.
        float yawDeg = horizontal > 1.0e-4
                ? (float) Math.toDegrees(Math.atan2(-f.x, -f.z))
                : entity.getYRot();

        poseStack.mulPose(Axis.YP.rotationDegrees(yawDeg));
        // -90 lays the model flat (normal pointing up); adding the flight
        // pitch tilts the whole plane, keeping the incidence angle.
        poseStack.mulPose(Axis.XP.rotationDegrees(pitchDeg - 90.0f));

        // Spin the texture around the disc's own normal.
        float spin = (entity.tickCount + partialTicks) * 30.0f;
        poseStack.mulPose(Axis.ZP.rotationDegrees(spin));
        // ItemDisplayContext.NONE applies no display transform, so the model
        // stays centred exactly on the origin and the spin is right on the
        // disc's centre. (GROUND would lift the model by 0.125 inside the
        // spin, making the centre orbit; its 0.5 scale is folded into the
        // 0.7 below to keep the same rendered size.)
        poseStack.scale(0.7f, 0.7f, 0.7f);

        this.itemRenderer.renderStatic(entity.getItem(), ItemDisplayContext.NONE,
                packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer,
                entity.level(), entity.getId());
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(DiscProjectileEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
