package com.blaxout1213.dayz;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

import com.blaxout1213.dayz.CustomPotion;
import com.blaxout1213.dayz.bitcoin.*;
import com.blaxout1213.dayz.bloodbags.*;
import com.blaxout1213.dayz.packet.PacketPipeline;
import com.blaxout1213.dayz.packet.SyncProperties;
import com.blaxout1213.dayz.packet.SyncPropertiesHandler;
import com.blaxout1213.entity.EntityBoulderSpider;
import com.blaxout1213.entity.EntityCivilianSteve;
import com.blaxout1213.entity.EntityCorruptedZombie;
import com.blaxout1213.entity.EntityDovahkiin;
import com.blaxout1213.entity.EntityEvilSteave;
import com.blaxout1213.entity.EntityGreenCreep;
import com.blaxout1213.entity.EntityPhoenix;
import com.blaxout1213.entity.EntityPoisonSpider;
import com.blaxout1213.entity.EntityRedCreep;
import com.blaxout1213.entity.EntitySuicideSpider;
import com.blaxout1213.items.BloodTest;
import com.blaxout1213.items.ItemAcidBucket;
import com.blaxout1213.items.ItemBandage;
import com.blaxout1213.items.ItemEndSword;
import com.blaxout1213.items.ItemEpiPen;
import com.blaxout1213.items.ItemNetherStick;
import com.blaxout1213.items.ItemNightStick;
import com.blaxout1213.items.ItemPhone;
import com.blaxout1213.items.ItemTowel;
import com.blaxout1213.items.VenomSword;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dayz.proxy.CommonProxy;

@Mod(modid = DayzMod.MODID, version = DayzMod.VERSION)
public class DayzMod
{
	//@SidedProxy(clientSide="com.blaxout1213.dayz.ClientProxy", serverSide="com.blaxout1213.dayz.CommonProxy")
    public static final String MODID = "Venom";
    public static final String VERSION = "0.1";
    public static final PacketPipeline packetPipeline = new PacketPipeline();
    public static SimpleNetworkWrapper network;
    //public static Potion PotionBleeding = (new Potion(8, false, 7889559)).setPotionName("potion.jump").setIconIndex(2, 1);;
	public static DamageSource venom = new DamageSource("venom").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource zombieInfection = new DamageSource("zombieinfection").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource sickness = new DamageSource("sickness").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource corruption = new DamageSource("corruption").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource badBlood = new DamageSource("badblood").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource wet = new DamageSource("wet").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource bleed = new DamageSource("bleed").setDamageBypassesArmor();
	public static DamageSource error = new DamageSource("error").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource hot = new DamageSource("hot").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource cold = new DamageSource("cold").setDamageBypassesArmor().setDamageIsAbsolute();
	public static DamageSource acid = new DamageSource("acid").setDamageBypassesArmor();
	
	public static Item antidote;
	public static Item bloodTest;
	
	public static Item bitcoinWood;
	public static Item bitcoinBronze;
	public static Item bitcoinSilver;
	public static Item bitcoinGold;
	public static Item bitcoinPlatnium;
	public static Item bitcoinOrange;
	
	public static Item bloodBagOplus;
	public static Item bloodBagOminus;
	public static Item bloodBagAplus;
	public static Item bloodBagAminus;
	public static Item bloodBagBplus;
	public static Item bloodBagBminus;
	public static Item bloodBagABplus;
	public static Item bloodBagABminus;
	public static Item bloodBagRandom;
	
	public static Item bloodBagEmpty;
	
	public static Item antidote2;
	public static Item omelet;
	public static Item envenomedSword;
	public static Item endChunk;
	public static Item blowDart;
	public static Item dart;
	public static Block poisonCake;
	public static Block poisonCactus;
	public static Block miner;
	public static Block cpuMiner;
	public static Block gpuMiner;
	public static Block asicMiner;
	public static Block reinforcedDoor;
	public static BlockDarkWater darkWaterBlock;
	public static final String networkChannelName = "VenomMod";
	public static WorldGenPoisonCactus worldGenCactus = new WorldGenPoisonCactus();
	public static WorldGenCake worldGenCake = new WorldGenCake();
	public static GenerateBoulders boulderGen = new GenerateBoulders();
	public static WorldGenAcidfalls genLiquids = new WorldGenAcidfalls();
	public static WorldGenAcidLakes genLake = new WorldGenAcidLakes();
	public static WorldGenLargeDungeon genDungeon = new WorldGenLargeDungeon();
	public static Potion Potion;
	public static FMLEventChannel channel;
	@SidedProxy(clientSide="dayz.proxy.ClientProxy", serverSide="dayz.proxy.CommonProxy")
	public static CommonProxy proxy;
	@Instance(MODID)
	public static DayzMod instance;
	public static Item phone;
	public static Item nightStick;
	public static Item epiPen;
	private Item towel;
	private Potion illness;
	private Item seedCakeCooked;
	private Item seedCakeRaw;
	private Item endSword;
	private Item reinforcedDoorItem;
	public Fluid darkWater = new Fluid("Darkwater").setLuminosity(16).setDensity(6).setViscosity(200);
	public static int shockDataWatcher;
	public static float eclipseAverage;
	public static int acidGen;
	public static int corruptChance;
	public static double illnessRange;
	public static boolean removeHoes;
	public static float easyMod;
	public static float normMod;
	public static float hardMod;
	public static float hardCMod;
	public static int steves;
	public static int poisonSpiders;
	
	public static Item bandage;
	public static Block curroption;
	public static Block boulder;
	public static Item woodnightStick;
	public static Item acidBucket;
	public static Item netherStick;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	FluidRegistry.registerFluid(darkWater);
    	//PotionBleeding = new PotionBleeding(21, false, 0).setIconIndex(4, 0).setPotionName("potion.potionName");
    	antidote = new AntiDote().setTextureName("dayz:antidote1");
    	bloodTest = new BloodTest().setTextureName("dayz:bloodtest");
    	
    	bloodBagOminus = new BloodBagOMinus().setTextureName("dayz:bloodbagf");
    	bloodBagOplus = new BloodBagOPlus().setTextureName("dayz:bloodbagf");
    	bloodBagAminus = new BloodBagAMinus().setTextureName("dayz:bloodbagf");
    	bloodBagAplus = new BloodBagAPlus().setTextureName("dayz:bloodbagf");
    	bloodBagBminus = new BloodBagBMinus().setTextureName("dayz:bloodbagf");
    	bloodBagBplus = new BloodBagBPlus().setTextureName("dayz:bloodbagf");
    	bloodBagABminus = new BloodBagABMinus().setTextureName("dayz:bloodbagf");
    	bloodBagABplus = new BloodBagABPlus().setTextureName("dayz:bloodbagf");
    	bloodBagRandom = new BloodBagRandom().setTextureName("dayz:bloodbagf");
    	bloodBagEmpty = new BloodBagEmpty().setTextureName("dayz:bloodbagem").setMaxStackSize(1);
    	
    	bitcoinWood = new BitcoinWood().setTextureName("dayz:bWood");
    	bitcoinBronze = new BitcoinBronze().setTextureName("dayz:bBronze");
    	bitcoinSilver = new BitcoinSilver().setTextureName("dayz:bSilver");
    	bitcoinGold = new BitcoinGold().setTextureName("dayz:bGold");
    	bitcoinPlatnium = new BitcoinPlatnium().setTextureName("dayz:bPlat");
    	bitcoinOrange = new BitcoinOrange().setTextureName("dayz:bOrange");
    	
    	omelet = new ItemFood(10, 1F ,false).setTextureName("dayz:omelet").setUnlocalizedName("omelet");
    	seedCakeCooked = new ItemFood(2, 1F ,false).setTextureName("dayz:seedcakecook").setUnlocalizedName("seedcakecooked");
    	seedCakeRaw = new ItemFood(1, 1F ,false).setTextureName("dayz:seedcakeraw").setUnlocalizedName("seedcakeraw");
    	antidote2 = new AntiDoteC2().setTextureName("dayz:antidotec2");
    	envenomedSword = new VenomSword(ToolMaterial.EMERALD).setTextureName("dayz:venomsword");
    	endChunk = new Item().setTextureName("dayz:endchunk").setUnlocalizedName("endchunk");
    	epiPen = new ItemEpiPen().setTextureName("dayz:epipen");
    	nightStick = new ItemNightStick().setTextureName("dayz:knockoutstick");
    	woodnightStick = new ItemNightStick().setTextureName("dayz:woodknockoutstick").setMaxDamage(10).setUnlocalizedName("woodnightstick");
    	towel = new ItemTowel().setTextureName("dayz:towel");
    	bandage = new ItemBandage().setTextureName("dayz:bandage");
    	boulder = new BlockBoulder().setBlockName("boulder").setCreativeTab(CreativeTabs.tabMisc);
    	endSword = new ItemEndSword(ToolMaterial.EMERALD).setTextureName("dayz:endsword");
    	darkWaterBlock = new BlockDarkWater(darkWater, Material.water);
    	acidBucket = new ItemAcidBucket(darkWaterBlock).setContainerItem(Items.bucket).setTextureName("dayz:bucket_acid").setUnlocalizedName("acidbucket");
    	//dart = new Item().setTextureName("dayz:dart").setUnlocalizedName("dart");
    	//blowDart = new BlowDart().setTextureName("dayz:blowdart").setUnlocalizedName("blowdart");
    	poisonCake = new PoisonCake().setCreativeTab(CreativeTabs.tabCombat).setBlockName("poisoncake");
    	poisonCactus = new PoisonCactus().setCreativeTab(CreativeTabs.tabCombat).setBlockName("poisoncactus");
    	curroption = new BlockCurroption().setCreativeTab(CreativeTabs.tabCombat).setBlockName("curroption").setStepSound(Block.soundTypeGrass).setHardness(0.4F);
    	miner = new BlockBitcoinMiner().setCreativeTab(CreativeTabs.tabMisc).setBlockTextureName("dayz:minerblock").setBlockName("bitcoinminer");
    	cpuMiner = new BlockBitcoinMinerT1().setCreativeTab(CreativeTabs.tabMisc).setBlockTextureName("dayz:minerblock").setBlockName("cpuminer");
    	gpuMiner = new BlockBitcoinMinerT2().setCreativeTab(CreativeTabs.tabMisc).setBlockTextureName("dayz:minerblock").setBlockName("gpuminer");
    	asicMiner = new BlockBitcoinMinerT3().setCreativeTab(CreativeTabs.tabMisc).setBlockTextureName("dayz:minerblock").setBlockName("asicminer");
    	reinforcedDoor = new BlockReinforcedDoor().setCreativeTab(CreativeTabs.tabMisc).setBlockName("reinforceddoor").setHardness(3.0F).setStepSound(Block.soundTypeWood).setBlockName("doorWood").setBlockTextureName("door_reinforced");
    	
    	phone = new ItemPhone().setTextureName("dayz:phone");
    	reinforcedDoorItem = new ItemReinforcedDoor(this.reinforcedDoor).setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("reinforcedooritem");
    	netherStick = new ItemNetherStick().setTextureName("dayz:netherstick").setCreativeTab(CreativeTabs.tabCombat);
    	
    	GameRegistry.registerItem(endChunk, "endchunk");
    	GameRegistry.registerItem(antidote, "antidote");
    	GameRegistry.registerItem(bloodTest, "bloodtest");
    	GameRegistry.registerItem(epiPen, "epipen");
    	GameRegistry.registerItem(nightStick, "nightstick");
    	GameRegistry.registerItem(woodnightStick, "woodnightstick");
    	GameRegistry.registerItem(towel, "towel");
    	GameRegistry.registerItem(seedCakeCooked, "seedcakecooked");
    	GameRegistry.registerItem(seedCakeRaw, "seedcakeraw");
    	
    	GameRegistry.registerItem(bloodBagOminus, "bloodbagO-");
    	GameRegistry.registerItem(bloodBagOplus, "bloodbagO+");
    	GameRegistry.registerItem(bloodBagAminus, "bloodbagA-");
    	GameRegistry.registerItem(bloodBagAplus, "bloodbagA+");
    	GameRegistry.registerItem(bloodBagBminus, "bloodbagB-");
    	GameRegistry.registerItem(bloodBagBplus, "bloodbagB+");
    	GameRegistry.registerItem(bloodBagABminus, "bloodbagAB-");
    	GameRegistry.registerItem(bloodBagABplus, "bloodbagAB+");
    	GameRegistry.registerItem(bloodBagRandom, "bloodbagR");
    	GameRegistry.registerItem(bloodBagEmpty, "bloodbagE");
    	
    	GameRegistry.registerItem(bitcoinWood, "bitWood");
    	GameRegistry.registerItem(bitcoinBronze, "bitBronze");
    	GameRegistry.registerItem(bitcoinSilver, "bitSilver");
    	GameRegistry.registerItem(bitcoinGold, "bitGold");
    	GameRegistry.registerItem(bitcoinPlatnium, "bitPlat");
    	GameRegistry.registerItem(bitcoinOrange, "bitOrange");
    	
    	GameRegistry.registerItem(antidote2, "antidote2");
    	GameRegistry.registerItem(omelet, "omelet");
    	GameRegistry.registerItem(envenomedSword, "venomsword");
    	GameRegistry.registerItem(netherStick, "netherstick");
    	//GameRegistry.registerItem(reinforcedDoorItem, "RDI");
    	//GameRegistry.registerItem(blowDart, "blowdart");
    	GameRegistry.registerBlock(poisonCake, "poisoncake");
    	GameRegistry.registerBlock(poisonCactus, "poisoncactus");
    	GameRegistry.registerBlock(miner, "bitcoinminer");
    	GameRegistry.registerBlock(cpuMiner, "cpuminer");
    	GameRegistry.registerBlock(gpuMiner, "gpuminer");
    	GameRegistry.registerBlock(asicMiner, "asicminer");
    	GameRegistry.registerBlock(curroption, "curroption");
    	GameRegistry.registerBlock(boulder, "boulder");
    	GameRegistry.registerBlock(reinforcedDoor, "reinforcedDoor");
    	GameRegistry.registerItem(phone, "phone");
    	GameRegistry.registerItem(endSword, "endsword");
    	GameRegistry.registerItem(bandage, "bandage");
    	GameRegistry.registerItem(acidBucket, "acidbucket");
    	GameRegistry.registerBlock(darkWaterBlock, "darkwater");
    	FluidContainerRegistry.registerFluidContainer(darkWater, new ItemStack(acidBucket), new ItemStack(Items.bucket));
    	
    	//GameRegistry.registerWorldGenerator(worldGen, 1);
    	DimensionManager.unregisterProviderType(0);
    	DimensionManager.registerProviderType(0, WorldProviderSurface2.class, true);
    	//DimensionManager.registerDimension(1, WorldProviderSurface2);
    	GameRegistry.addSmelting(seedCakeRaw, new ItemStack(seedCakeCooked), 0F);
    	ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(antidote),1,1,65));
    	ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(antidote),1,1,15));
    	proxy.registerRenderers();
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	corruptChance = config.getInt("ZombieInfectChance", "Game_Mechanics", 250, 1, 1000, "1 in x Chance of zombie infection");
    	illnessRange = config.get("Game_Mechanics", "IllnessRange", 4, "Range of the illness potion effect [range: 1.5 ~ 64, default: 4]", 1.5, 64).getDouble();
    	removeHoes = config.getBoolean("RemoveHoes", "Game_Mechanics", true, "Wether or not to remove hoes");
    	eclipseAverage = config.getFloat("SolarEclipseRarity", "Game_Mechanics", 7, 1, 365, "On average, how far apart solar eclipses are (In minecraft days)");
    	acidGen = config.getInt("AcidRunoffRarity", "Game_Mechanics", 12, 2, 32, "How often acid runoffs are generated, higher = more");
    	easyMod = config.getFloat("EasyModifer", "Difficulty_Modifiers", 0.85F, 0.5F, 3F, "Modifier for easy difficulty");
    	normMod = config.getFloat("NormalModifer", "Difficulty_Modifiers", 1.0F, 0.5F, 3F, "Modifier for normal difficulty");
    	hardMod = config.getFloat("HardModifer", "Difficulty_Modifiers", 1.15F, 0.5F, 3F, "Modifier for hard difficulty");
    	hardCMod = config.getFloat("HardcoreModifier", "Difficulty_Modifiers", 1.35F, 0.5F, 3F, "Modifier for hardcore mode");
    	steves = config.getInt("SteveSpawnRate", "Spawn_Rates", 30, 1, 100, "Spawn rate for steves (Zombies are 100)");
    	poisonSpiders = config.getInt("PoisonSpiderSpawns", "Spawn_Rates", 35, 1, 100, "Spawn rate for poisonous spiders (Zombies are 100)");
    	shockDataWatcher = config.getInt("ShockDataWatcher", "Data_Watchers", 20, 20, 31, "Datawatcher ID for shock value");
    	config.save();
    	
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//GameRegistry.registerWorldGenerator(genDungeon, 30);
    	GameRegistry.registerWorldGenerator(worldGenCactus, 6);
    	GameRegistry.registerWorldGenerator(worldGenCake, 6);
    	GameRegistry.registerWorldGenerator(boulderGen, 7);
    	GameRegistry.registerWorldGenerator(genLake, 30);
    	GameRegistry.registerWorldGenerator(genLiquids, 30);
    	GameRegistry.addShapelessRecipe(new ItemStack(this.bitcoinWood, 10), new ItemStack (this.bitcoinBronze));
    	GameRegistry.addShapelessRecipe(new ItemStack(this.bitcoinBronze, 10), new ItemStack (this.bitcoinSilver));
    	GameRegistry.addShapelessRecipe(new ItemStack(this.bitcoinSilver, 10), new ItemStack (this.bitcoinGold));
    	GameRegistry.addShapelessRecipe(new ItemStack(this.bitcoinGold, 10), new ItemStack (this.bitcoinPlatnium));
    	GameRegistry.addShapelessRecipe(new ItemStack(this.bitcoinPlatnium, 10), new ItemStack (this.bitcoinOrange));
    	GameRegistry.addShapelessRecipe(new ItemStack(Blocks.dirt), new ItemStack (this.curroption));
    	GameRegistry.addShapelessRecipe(new ItemStack(this.bloodBagEmpty), new ItemStack (this.bloodBagRandom));
    	this.registerEntity(EntityPoisonSpider.class, "PoisonSpider");
    	this.registerEntity(EntityCorruptedZombie.class, "CorruptZombie");
    	this.registerEntity(EntitySuicideSpider.class, "SuicideSpider");
    	this.registerEntity(EntityEvilSteave.class, "EvilSteave");
    	this.registerEntity(EntityCivilianSteve.class, "CivieSteve");
    	this.registerEntity(EntityBoulderSpider.class, "BoulderSpider");
    	this.registerEntity(EntityRedCreep.class, "RedCreep");
    	this.registerEntity(EntityGreenCreep.class, "GreenCreep");
    	this.registerEntity(EntityDovahkiin.class, "Dovahkiin");
    	this.registerEntity(EntityPhoenix.class, "Phoenix");
    	//this.registerEntity(EntityFallingBoulder.class, "DontSpawn");
    	
    	GameRegistry.addRecipe(new ItemStack(antidote), new Object[]{
            " AC",
            "ABA",
            "AA ",
            'A', Items.iron_ingot,
            'B', Items.fermented_spider_eye,
            'C', Items.blaze_powder
    });
    	GameRegistry.addRecipe(new ItemStack(antidote2), new Object[]{
            " AC",
            "ABA",
            "AA ",
            'A', Items.iron_ingot,
            'B', Items.spider_eye,
            'C', Blocks.planks
    });
    	GameRegistry.addRecipe(new ItemStack(omelet), new Object[]{
            "EEE",
            "MSM",
            "EEE",
            'E', Items.egg,
            'S', Items.cooked_beef,
            'M', Items.milk_bucket
    });
    	GameRegistry.addRecipe(new ItemStack(envenomedSword), new Object[]{
            "E E",
            "E  ",
            "EEE",
            'E', Blocks.diamond_block
    });
    	GameRegistry.addRecipe(new ItemStack(bloodTest), new Object[]{
            "EE ",
            "EI ",
            "  I",
            'E', Blocks.wool,
            'I', Items.iron_ingot
    });
    	GameRegistry.addRecipe(new ItemStack(bloodBagEmpty), new Object[]{
            "EEE",
            "ETE",
            "IEE",
            'E', Blocks.wool,
            'I', Items.iron_ingot,
            'T', bloodTest
    });
    	GameRegistry.addRecipe(new ItemStack(miner), new Object[]{
            "EEE",
            "ITI",
            "EEE",
            'E', Blocks.redstone_block,
            'I', Items.comparator,
            'T', bitcoinBronze
    });
    	GameRegistry.addRecipe(new ItemStack(cpuMiner), new Object[]{
            " E ",
            "ITI",
            "EBE",
            'E', Items.gold_ingot,
            'I', Items.comparator,
            'T', miner,
            'B', bitcoinSilver
    });
    	GameRegistry.addRecipe(new ItemStack(gpuMiner), new Object[]{
            " E ",
            "ITI",
            "EBE",
            'E', Items.diamond,
            'I', Items.comparator,
            'T', cpuMiner,
            'B', bitcoinGold
    });
    	GameRegistry.addRecipe(new ItemStack(asicMiner), new Object[]{
            " E ",
            "ITI",
            "EBE",
            'E', Items.blaze_rod,
            'I', Blocks.diamond_block,
            'T', gpuMiner,
            'B', bitcoinPlatnium
    });
    	GameRegistry.addRecipe(new ItemStack(woodnightStick), new Object[]{
            "  E",
            " EB",
            "E E",
            'E', Items.stick,
            'B', bitcoinBronze
    });
    	GameRegistry.addRecipe(new ItemStack(nightStick), new Object[]{
            "NT",
            "BB",
            'N', woodnightStick,
            'B', bitcoinSilver,
            'T', bitcoinBronze
    });
    	GameRegistry.addRecipe(new ItemStack(towel), new Object[]{
            "WW",
            "WW",
            'W', Blocks.wool
    });
    	GameRegistry.addRecipe(new ItemStack(epiPen), new Object[]{
            "  I",
            " S ",
            "S  ",
            'S', Items.sugar,
            'I', Items.iron_ingot
    });
    	GameRegistry.addRecipe(new ItemStack(seedCakeRaw), new Object[]{
            "SW",
            " S",
            'W', Items.wheat,
            'S', Items.wheat_seeds
    });
    	GameRegistry.addRecipe(new ItemStack(bandage), new Object[]{
            "WWW",
            "  S",
            'W', Blocks.wool,
            'S', Items.string
    });
    	GameRegistry.addRecipe(new ItemStack(endSword), new Object[]{
            "S",
            "S",
            "W",
            'W', Items.stick,
            'S', endChunk
    });
    	if(removeHoes)
    	{
        	RecipeRemover.removeAnyRecipe(new ItemStack(Items.wooden_hoe));
        	RecipeRemover.removeAnyRecipe(new ItemStack(Items.stone_hoe));
        	RecipeRemover.removeAnyRecipe(new ItemStack(Items.iron_hoe));
        	RecipeRemover.removeAnyRecipe(new ItemStack(Items.diamond_hoe));
        	RecipeRemover.removeAnyRecipe(new ItemStack(Items.golden_hoe));
    	}
    	//network = NetworkRegistry.INSTANCE.newSimpleChannel(DayzMod.networkChannelName);
    	//network.registerMessage(SyncPropertiesHandler.class, SyncProperties.class, 0, Side.CLIENT);
    	DayzMod.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(DayzMod.networkChannelName);
    	CustomPotion.illness = (CustomPotion)new CustomPotion(25, true, 3035801).setPotionName("potion.sickness");
    	CustomPotion.badblood = (CustomPotion)new CustomPotion(26, true, 4393481).setPotionName("potion.badblood");
    	CustomPotion.corruption = (CustomPotion)new CustomPotion(27, true, 4393481).setPotionName("potion.corruption");
    	CustomPotion.netherBound = (CustomPotion)new CustomPotion(28, true, 4393481).setPotionName("potion.nether");
    	CustomPotion.tranquilized = (CustomPotion)new CustomPotion(29, true, 4393481).setPotionName("potion.tranquilized");

    	
    	 packetPipeline.initialise();
    	 EntityRegistry.addSpawn(EntityPoisonSpider.class, poisonSpiders, 4, 4, EnumCreatureType.monster, BiomeGenBase.taigaHills, BiomeGenBase.jungle, 
    			 BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.forest, 
    			 BiomeGenBase.forestHills, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.beach, 
    			 BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.megaTaiga,
    			 BiomeGenBase.megaTaigaHills, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F,
    			 BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.stoneBeach, BiomeGenBase.coldTaiga,
    			 BiomeGenBase.coldTaigaHills, BiomeGenBase.jungleEdge, BiomeGenBase.roofedForest);
    	 EntityRegistry.addSpawn(EntityCorruptedZombie.class, 125, 4, 4, EnumCreatureType.monster, BiomeGenBase.taigaHills, BiomeGenBase.jungle, 
    			 BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.forest, 
    			 BiomeGenBase.forestHills, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.beach, 
    			 BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.megaTaiga,
    			 BiomeGenBase.megaTaigaHills, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F,
    			 BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.stoneBeach, BiomeGenBase.coldTaiga,
    			 BiomeGenBase.coldTaigaHills, BiomeGenBase.jungleEdge, BiomeGenBase.roofedForest);
    	 EntityRegistry.addSpawn(EntityEvilSteave.class, steves, 1, 1, EnumCreatureType.monster, BiomeGenBase.taigaHills, BiomeGenBase.jungle, 
    			 BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.forest, 
    			 BiomeGenBase.forestHills, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.beach, 
    			 BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.megaTaiga,
    			 BiomeGenBase.megaTaigaHills, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F,
    			 BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.stoneBeach, BiomeGenBase.coldTaiga,
    			 BiomeGenBase.coldTaigaHills, BiomeGenBase.jungleEdge, BiomeGenBase.roofedForest, BiomeGenBase.hell);
    	 EntityRegistry.addSpawn(EntityCivilianSteve.class, 10, 1, 1, EnumCreatureType.monster, BiomeGenBase.taigaHills, BiomeGenBase.jungle, 
    			 BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.forest, 
    			 BiomeGenBase.forestHills, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.beach, 
    			 BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.megaTaiga,
    			 BiomeGenBase.megaTaigaHills, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F,
    			 BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.stoneBeach, BiomeGenBase.coldTaiga,
    			 BiomeGenBase.coldTaigaHills, BiomeGenBase.jungleEdge, BiomeGenBase.roofedForest);
    	 /*EntityRegistry.addSpawn(EntityGreenCreep.class, 10, 1, 1, EnumCreatureType.monster, BiomeGenBase.taigaHills, BiomeGenBase.jungle, 
    			 BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.forest, 
    			 BiomeGenBase.forestHills, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.beach, 
    			 BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.megaTaiga,
    			 BiomeGenBase.megaTaigaHills, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F,
    			 BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.stoneBeach, BiomeGenBase.coldTaiga,
    			 BiomeGenBase.coldTaigaHills, BiomeGenBase.jungleEdge, BiomeGenBase.roofedForest);
    	 EntityRegistry.addSpawn(EntityRedCreep.class, 10, 1, 1, EnumCreatureType.monster, BiomeGenBase.taigaHills, BiomeGenBase.jungle, 
    			 BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.forest, 
    			 BiomeGenBase.forestHills, BiomeGenBase.swampland, BiomeGenBase.river, BiomeGenBase.beach, 
    			 BiomeGenBase.desert, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.megaTaiga,
    			 BiomeGenBase.megaTaigaHills, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F,
    			 BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.stoneBeach, BiomeGenBase.coldTaiga,
    			 BiomeGenBase.coldTaigaHills, BiomeGenBase.jungleEdge, BiomeGenBase.roofedForest);
    			 */
    }
    @EventHandler
    public void postIinit(FMLPostInitializationEvent event)
    {
    	//GameRegistry.registerWorldGenerator(worldGen, 1);
    	FMLCommonHandler.instance().bus().register(new PlayerStalker());
    	MinecraftForge.EVENT_BUS.register(new PlayerStalker());
    	FillBucket.INSTANCE.buckets.put(darkWaterBlock, acidBucket);
    	MinecraftForge.EVENT_BUS.register(FillBucket.INSTANCE);
    	//MinecraftForge.EVENT_BUS.register(new GuiKnockOut(Minecraft.getMinecraft()));
    	packetPipeline.postInitialise();
    }
    public static void registerEntity(Class entityClass, String name)
    {
	    int entityID = EntityRegistry.findGlobalUniqueEntityId();
	    long seed = name.hashCode();
	    Random rand = new Random(seed);
	    int primaryColor = rand.nextInt() * 16777215;
	    int secondaryColor = rand.nextInt() * 16777215;
	
	    EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
	    EntityRegistry.registerModEntity(entityClass, name, entityID, instance, 64, 1, true);
	    EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
    }
}
