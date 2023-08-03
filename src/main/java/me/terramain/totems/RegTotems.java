package me.terramain.totems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;

public class RegTotems {

    public static void regTotems(){
        powerTotem();
        totemAntiWarden();
        flyTotem();
        fireballTotem();
        lightTotem();
        totemAntiFire();
        expTotem();
        slimeTotem();
        shieldTotem();
        ginocidTotem();
        minerTotem();
    }

    public static void powerTotem(){
        Totem.totemList.add(new Totem(
                "Тотем силы",
                List.of(
                        "При срабатывание: тотем наносит урон всем по близости.",
                        "При удержание: даёт силу."
                ),
                new Craft(List.of(
                        "A",
                        "B"
                ),List.of(
                        new CraftItem('A',Material.DIAMOND_SWORD),
                        new CraftItem('B',Material.TOTEM_OF_UNDYING)
                )),
                new TotemFunctions() {
                    @Override public void action(Player player) {

                        Location playerLoc = player.getLocation();
                        for (Entity entity : playerLoc.getWorld().getEntities()) {
                            if ( AddFunctions.getDistance(playerLoc,entity.getLocation()) > 25) return;
                            if (entity instanceof LivingEntity){
                                LivingEntity livingEntity = (LivingEntity) entity;
                                livingEntity.damage(14,player);
                            }
                        }

                    }
                    @Override public void onHead(Player player) {
                        if (!player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,3*20,2,true,true,true));
                        }
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void totemAntiWarden(){
        Totem.totemList.add(new Totem(
                "Вардено-отпугивательный тотем",
                List.of(
                        "При срабатывание: если сработает от вардена, то у вардена сброситься цель.",
                        "А также игрок получит скорость-2, прыгучисть-1, невидимость, защиту от урона на 10 секунд."
                ),
                new Craft(
                        List.of(
                                " # ",
                                "#A#",
                                " # "
                        ),
                        List.of(
                                new CraftItem('#',Material.ECHO_SHARD),
                                new CraftItem('A',Material.TOTEM_OF_UNDYING)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        System.out.println("action");

                        for (Entity entity : player.getWorld().getEntities()) {
                            if (AddFunctions.getDistance(player.getLocation(), entity.getLocation()) <= 80) {
                                if (entity instanceof Warden) {
                                    Warden warden = (Warden) entity;
                                    warden.setTarget(null);
                                    warden.addPotionEffect(new PotionEffect(
                                            PotionEffectType.SLOW,10*20,10));
                                }
                            }
                        }

                        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(){
                            public void run(){
                                player.addPotionEffect(new PotionEffect(
                                        PotionEffectType.SPEED,15*20,1));
                                player.addPotionEffect(new PotionEffect(
                                        PotionEffectType.JUMP,17*20,2));
                                player.addPotionEffect(new PotionEffect(
                                        PotionEffectType.INVISIBILITY,20*20,1));
                                player.addPotionEffect(new PotionEffect(
                                        PotionEffectType.DAMAGE_RESISTANCE,10*20,100));
                            }
                        },1);
                    }
                    @Override public void onHead(Player player) {
                        System.out.println("onHead");
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void flyTotem(){
        Totem.totemList.add(new Totem(
                "улитательный тотем",
                List.of(
                        "При срабатывание: Подкидывает игрока в небо, и даёт ему эффект плавного падения."
                ),
                new Craft(
                        List.of(
                                " * ",
                                "BAB",
                                " * "
                        ),
                        List.of(
                                new CraftItem('*',Material.FIREWORK_ROCKET),
                                new CraftItem('A',Material.TOTEM_OF_UNDYING),
                                new CraftItem('B',Material.FEATHER)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {

                        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(){public void run() {
                            player.addPotionEffect(new PotionEffect(
                                    PotionEffectType.LEVITATION, 3 * 20, 14, true, true, true));
                            player.addPotionEffect(new PotionEffect(
                                    PotionEffectType.SLOW_FALLING, 10 * 20, 2, true, true, true));

                            for ( Entity entity : player.getWorld().getEntities() ){
                                if (!(entity instanceof LivingEntity)) return;
                                if (AddFunctions.getDistance(player.getLocation(), entity.getLocation()) <= 10) {
                                    player.addPotionEffect(new PotionEffect(
                                            PotionEffectType.LEVITATION, 15, 14, true, true, true));
                                }
                            }
                        }},1);
                    }
                    @Override public void onHead(Player player) {

                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void fireballTotem(){
        Totem.totemList.add(new Totem(
                "фаирбольный тотем",
                List.of(
                        "При срабатывание: от игрока в направление всех мобов/игроков запустится фаирбол, как у эфрита.",
                        "При удержание: фаирболы гастов и эфритов будут отражаться."
                ),
                new Craft(
                        List.of("*A*"),
                        List.of(
                                new CraftItem('*',Material.FIRE_CHARGE),
                                new CraftItem('A',Material.TOTEM_OF_UNDYING)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        for ( Player playerOfWorld : player.getWorld().getPlayers() ){
                            if (AddFunctions.getDistance(player.getLocation(), playerOfWorld.getLocation()) <= 20) {
                                Location location =
                                        AddFunctions.theAngleFromAAlongTheAbscissaAxisToB(player.getLocation(),playerOfWorld.getLocation());
                                Fireball fireball = (Fireball) player.getWorld().spawnEntity(location,EntityType.FIREBALL);
                                fireball.setYield(3);
                                fireball.setIsIncendiary(true);
                                fireball.setIsIncendiary(true);
                                fireball.setHasLeftShooter(false);
                            }
                        }
                    }
                    @Override public void onHead(Player player) {

                        for ( Entity entity : player.getWorld().getEntities() ){
                            if (entity instanceof Fireball && !TotemData.deflectedFireballs.contains(entity.getUniqueId())) {
                                double distance = AddFunctions.getDistance(player.getLocation(), entity.getLocation());
                                if (distance < 5.1) {
                                    Fireball fireball = (Fireball) entity;
                                    Vector vector = fireball.getDirection().clone();
                                    vector.setX(-vector.getX());
                                    vector.setY(-vector.getY());
                                    vector.setZ(-vector.getZ());
                                    fireball.setDirection(vector);

                                    TotemData.deflectedFireballs.add(fireball.getUniqueId());
                                }
                            }
                        }
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void lightTotem(){
        Totem.totemList.add(new Totem(
                "световой тотем",
                List.of(
                        "При срабатывание: игроки по близости получают слепоту.",
                        "При удержание: даёт эффект ночного зрения"
                ),
                new Craft(
                        List.of(
                                "***",
                                "*A*",
                                "***"
                                ),
                        List.of(
                                new CraftItem('*',Material.TORCH),
                                new CraftItem('A',Material.TOTEM_OF_UNDYING)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        for ( Player playerOfWorld : player.getWorld().getPlayers() ){
                            if (AddFunctions.getDistance(player.getLocation(), playerOfWorld.getLocation()) <= 15 && playerOfWorld != player) {
                                playerOfWorld.addPotionEffect(new PotionEffect(
                                        PotionEffectType.BLINDNESS,15*20,2));
                            }
                        }
                        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(){public void run() {
                            player.addPotionEffect(new PotionEffect(
                                    PotionEffectType.NIGHT_VISION, 3*60*20, 1));
                        }},1);
                    }
                    @Override public void onHead(Player player) {
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.NIGHT_VISION, 11*20, 1));
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void totemAntiFire(){
        Totem.totemList.add(new Totem(
                "огнетушительный тотем",
                List.of(
                        "При срабатывание: поджигает всех по близости.",
                        "При удержание: даёт эффект огнестойкости."
                ),
                new Craft(
                        List.of(
                                "A",
                                "*"
                        ),
                        List.of(
                                new CraftItem('A',Material.TOTEM_OF_UNDYING),
                                new CraftItem('*',Material.WATER_BUCKET)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        Location location = player.getLocation();
                        Location block = location.clone();
                        for (int x = -10;x <= 10;x++) {
                            for (int y = -10;y <= 10;y++) {
                                for (int z = -10;z <= 10;z++) {
                                    block.setX(location.getX()+x);
                                    block.setY(location.getY()+y);
                                    block.setZ(location.getZ()+z);
                                    if (block.getBlock().getType()==Material.FIRE) block.getBlock().setType(Material.AIR);
                                }
                            }
                        }
                        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(){public void run() {
                            player.addPotionEffect(new PotionEffect(
                                    PotionEffectType.FIRE_RESISTANCE,3*30*20,1));
                        }},1);
                    }
                    @Override public void onHead(Player player) {
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FIRE_RESISTANCE,2*20,1));
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void expTotem(){
        Totem.totemList.add(new Totem(
                "опытный тотем",
                List.of(
                        "При срабатывание: отнимает по 30% уровней опыта у всех игроков по близости, передавая их игроку."
                ),
                new Craft(
                        List.of(
                                " * ",
                                "BAB",
                                " * "
                        ),
                        List.of(
                                new CraftItem('A',Material.TOTEM_OF_UNDYING),
                                new CraftItem('*',Material.EXPERIENCE_BOTTLE),
                                new CraftItem('B',Material.DIAMOND_AXE)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        for ( Player playerOfWorld : player.getWorld().getPlayers() ){
                            if (AddFunctions.getDistance(player.getLocation(), playerOfWorld.getLocation()) <= 10 && playerOfWorld != player) {
                                int level = playerOfWorld.getLevel();
                                int xp = (int)(level*0.3);
                                playerOfWorld.setLevel(level-xp);

                                player.setLevel(player.getLevel()+xp);
                            }
                        }
                    }
                    @Override public void onHead(Player player) {

                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void slimeTotem(){
        Totem.totemList.add(new Totem(
                "слайм тотем",
                List.of(
                        "При срабатывание: все игроки поблизости будут измазаны слизью",
                        "(плавное падение и медлительность)",
                        "При удержание: даёт прыгучесть и защиту от урона от падения"
                ),
                new Craft(
                        List.of(
                                " * ",
                                "*A*",
                                " * "
                        ),
                        List.of(
                                new CraftItem('A',Material.TOTEM_OF_UNDYING),
                                new CraftItem('*',Material.SLIME_BALL)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        for (Entity entity : player.getWorld().getEntities()) {
                            if ( AddFunctions.getDistance(player.getLocation(),entity.getLocation()) > 25) return;
                            if (entity instanceof LivingEntity){
                                LivingEntity livingEntity = (LivingEntity) entity;
                                Location location = livingEntity.getLocation();
                                for (int x = -1; x <= 1; x++) {
                                    for (int y = -1; y <= 2; y++) {
                                        for (int z = -1; z <= 1; z++) {
                                            Location block = location.clone();
                                            block.setX(block.getX()+x);
                                            block.setY(block.getY()+y);
                                            block.setZ(block.getZ()+z);
                                            if (block.getBlock().getType() != Material.AIR && block.getBlock().getType() != Material.LAVA){
                                                block.getBlock().setType(Material.SLIME_BLOCK);
                                            }
                                        }
                                    }
                                }
                                Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(){public void run() {
                                    livingEntity.addPotionEffect(new PotionEffect(
                                            PotionEffectType.SLOW_DIGGING,8*20,3));
                                }},1);
                            }
                        }
                    }
                    @Override public void onHead(Player player) {
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.JUMP,2*20,2));
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void shieldTotem(){
        Totem.totemList.add(new Totem(
                "тотем щита",
                List.of(
                        "При срабатывание: накладывает эффект сопротивления на 10 секунд",
                        "Пкм: щит"
                ),
                new Craft(
                        List.of(
                                "*B*",
                                "*A*"
                        ),
                        List.of(
                                new CraftItem('A',Material.TOTEM_OF_UNDYING),
                                new CraftItem('B',Material.SHIELD),
                                new CraftItem('*',Material.IRON_INGOT)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {

                    }
                    @Override public void onHead(Player player) {

                    }
                    @Override public void rightClick(Player player) {
                        if ( player.getInventory().getItemInMainHand().getType()==Material.TOTEM_OF_UNDYING ){
                            player.getInventory().getItemInMainHand().setType(Material.SHIELD);
                            TotemData.holdShieldPlayers.add(player);
                        }
                        else if ( player.getInventory().getItemInOffHand().getType()==Material.TOTEM_OF_UNDYING ){
                            player.getInventory().getItemInOffHand().setType(Material.SHIELD);
                            TotemData.holdShieldPlayers.add(player);
                        }
                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).regAllCrafts());
    }
    public static void ginocidTotem(){
        Totem.totemList.add(new Totem(
                "тотем геноцида",
                List.of(
                        "При срабатывание: накладывает очень много плохих эффектов на всех по близости",
                        "При удержание: даёт эффект силы, сопротивления, регенерация."
                ),
                new Craft(
                        List.of(
                                "ABC",
                                "DEF",
                                "G*#"
                        ),
                        List.of(
                                new CraftItem('A',Material.DIAMOND_LEGGINGS),
                                new CraftItem('B',Material.POPPED_CHORUS_FRUIT),
                                new CraftItem('C',Material.BOW),
                                new CraftItem('D',Material.TNT),
                                new CraftItem('E',Material.TOTEM_OF_UNDYING),
                                new CraftItem('F',Material.DIAMOND_SWORD),
                                new CraftItem('G',Material.DIAMOND),
                                new CraftItem('*',Material.END_CRYSTAL),
                                new CraftItem('#',Material.POINTED_DRIPSTONE)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        for ( Entity entity : player.getWorld().getEntities() ){
                            System.out.println(  AddFunctions.getDistance(player.getLocation(), entity.getLocation())  );
                            if (AddFunctions.getDistance(player.getLocation(), entity.getLocation()) <= 15 && entity != player) {
                                if (entity instanceof Monster || entity instanceof Player) {
                                    LivingEntity livingEntity = (LivingEntity) entity;
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 18 * 20, 1));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 6 * 20, 2));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 15 * 20, 1));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 60 * 3 * 20, 0));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 50 * 20, 0));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 15 * 20, 1));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 60 * 10 * 20, 4));
                                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 40 * 20, 0));
                                }
                            }
                        }
                        Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable(){public void run() {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,2*60*20,1));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,60*20,1));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,40*20,0));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,40*20,2));
                        }},1);
                    }
                    @Override public void onHead(Player player) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,2*20,0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,2*20,1));
                    }
                    @Override public void rightClick(Player player) {

                    }
                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {}
                }
        ).addCraft(new Craft(
                List.of(
                        "ABC",
                        "DEF",
                        "CGA"
                ),
                List.of(
                        new CraftItem('A',Material.TNT),
                        new CraftItem('B',Material.DIAMOND_AXE),
                        new CraftItem('C',Material.OBSIDIAN),
                        new CraftItem('D',Material.END_CRYSTAL),
                        new CraftItem('E',Material.TOTEM_OF_UNDYING),
                        new CraftItem('F',Material.SPECTRAL_ARROW),
                        new CraftItem('G',Material.COPPER_INGOT)
                )
        )).regAllCrafts());
    }
    public static void minerTotem(){
        Totem.totemList.add(new Totem(
                "шахтёрский тотем",
                List.of(
                        "При удержание: даёт эффект спешки. При зажатие шифта включается копание 1x2."
                ),
                new Craft(
                        List.of(
                                "ABC",
                                " * "
                        ),
                        List.of(
                                new CraftItem('A',Material.GLOW_BERRIES),
                                new CraftItem('B',Material.IRON_INGOT),
                                new CraftItem('C',Material.DIAMOND_PICKAXE),
                                new CraftItem('*',Material.TOTEM_OF_UNDYING)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {

                    }
                    @Override public void onHead(Player player) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,10,1));
                        if (player.isSneaking()){

                        }
                    }
                    @Override public void rightClick(Player player) {

                    }

                    @Override public void custom1(Player player, String arg, Object[] argsObjects) {
                        if (TotemData.breakBlockWithMineTotemPlayers.contains(player)) {
                            TotemData.breakBlockWithMineTotemPlayers.remove(player);
                            return;
                        }
                        System.out.println("custom1");
                        if (arg.equals("mineblock")){
                            if (player.isSneaking()) {
                                Location block = (Location) argsObjects[0];
                                block.setY(block.getY() - 1);
                                player.sendMessage("прочность: " + block.getBlock().getBlockPower());
                                player.breakBlock(block.getBlock());
                                TotemData.breakBlockWithMineTotemPlayers.add(player);
                            }
                        }
                    }
                }
        ).addCraft(new Craft(
                List.of(
                        "ACB",
                        " * "
                ),
                List.of(
                        new CraftItem('A',Material.GLOW_BERRIES),
                        new CraftItem('B',Material.IRON_INGOT),
                        new CraftItem('C',Material.DIAMOND_PICKAXE),
                        new CraftItem('*',Material.TOTEM_OF_UNDYING)
                )
        )).addCraft(new Craft(
                List.of(
                        "BAC",
                        " * "
                ),
                List.of(
                        new CraftItem('A',Material.GLOW_BERRIES),
                        new CraftItem('B',Material.IRON_INGOT),
                        new CraftItem('C',Material.DIAMOND_PICKAXE),
                        new CraftItem('*',Material.TOTEM_OF_UNDYING)
                )
        )).addCraft(new Craft(
                List.of(
                        "BCA",
                        " * "
                ),
                List.of(
                        new CraftItem('A',Material.GLOW_BERRIES),
                        new CraftItem('B',Material.IRON_INGOT),
                        new CraftItem('C',Material.DIAMOND_PICKAXE),
                        new CraftItem('*',Material.TOTEM_OF_UNDYING)
                )
        )).addCraft(new Craft(
                List.of(
                        "CAB",
                        " * "
                ),
                List.of(
                        new CraftItem('A',Material.GLOW_BERRIES),
                        new CraftItem('B',Material.IRON_INGOT),
                        new CraftItem('C',Material.DIAMOND_PICKAXE),
                        new CraftItem('*',Material.TOTEM_OF_UNDYING)
                )
        )).addCraft(new Craft(
                List.of(
                        "CBA",
                        " * "
                ),
                List.of(
                        new CraftItem('A',Material.GLOW_BERRIES),
                        new CraftItem('B',Material.IRON_INGOT),
                        new CraftItem('C',Material.DIAMOND_PICKAXE),
                        new CraftItem('*',Material.TOTEM_OF_UNDYING)
                )
        )).regAllCrafts());

    }
    //public static void
    /*public static void gdz(){
        Totem.totemList.add(new Totem(
                " тотем",
                List.of(
                        "При срабатывание: ",
                        "При удержание: "
                ),
                new Craft(
                        List.of(
                                "",
                                "",
                                ""
                        ),
                        List.of(
                                new CraftItem('',Material.),
                                new CraftItem('',Material.)
                        )
                ),
                new TotemFunctions() {
                    @Override public void action(Player player) {

                    }
                    @Override public void onHead(Player player) {

                    }
                }
        ));
    }*/
}
