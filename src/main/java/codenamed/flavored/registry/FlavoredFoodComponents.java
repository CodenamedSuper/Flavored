package codenamed.flavored.registry;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FlavoredFoodComponents {


    public static final FoodComponent HONEY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2f).build();

    public static final FoodComponent OIL = new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f).build();

    public static final FoodComponent TOMATO_PUREE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3f).build();


    public static final FoodComponent TOMATO = new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f).build();

    public static final FoodComponent CUCUMBER = new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f).build();

    public static final FoodComponent PEPPER = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2f).build();

    public static final FoodComponent CORN = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2f).build();

    public static final FoodComponent GARLIC = new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA), 0.33f).build();

    public static final FoodComponent CAULIFLOWER_CURD = new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA), 0.33f).build();

}
