package me.terramain.totems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        RegTotems.regTotems();

        new CMD();
        Bukkit.getPluginManager().registerEvents(new ActivateTotemEvent(),this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, Totem::testsAllPlayers,5L,5L);

        //ItemStack helmet;
        //int random = (int)(Math.random()*3);
        //if (random==0) helmet=new ItemStack(Material.IRON_HELMET);
        //if (random==1) helmet=new ItemStack(Material.DIAMOND_HELMET);
        //if (random==2) helmet=new ItemStack(Material.NETHERITE_HELMET);
        //ItemStack chestplate;
        //random = (int)(Math.random()*3);
        //if (random==0) chestplate=new ItemStack(Material.IRON_HELMET);
        //if (random==1) chestplate=new ItemStack(Material.DIAMOND_HELMET);
        //if (random==2) chestplate=new ItemStack(Material.NETHERITE_HELMET);
        //ItemStack leggings;
        //random = (int)(Math.random()*3);
        //if (random==0) leggings=new ItemStack(Material.IRON_LEGGINGS);
        //if (random==1) leggings=new ItemStack(Material.DIAMOND_LEGGINGS);
        //if (random==2) leggings=new ItemStack(Material.NETHERITE_LEGGINGS);
        //ItemStack boots;
        //random = (int)(Math.random()*3);
        //if (random==0) boots=new ItemStack(Material.IRON_BOOTS);
        //if (random==1) boots=new ItemStack(Material.DIAMOND_BOOTS);
        //if (random==2) boots=new ItemStack(Material.NETHERITE_BOOTS);

    }

    @Override
    public void onDisable() {

    }

    /*public void regAllTotems(){
        Bukkit.getPluginManager().registerEvents(new ActivateTotemEvent(),this);

        Totem.totemList.add(new Totem(
                "улитательный тотем",
                List.of(
                        "При срабатывание: Подкидывает игрока в небо, и даёт ему эффект плавного падения."
                ),
                new Material[][]{
                        {null,Material.FIREWORK_ROCKET,null},
                        {null,Material.TOTEM_OF_UNDYING,null},
                        {Material.PHANTOM_MEMBRANE,Material.PHANTOM_MEMBRANE,Material.PHANTOM_MEMBRANE}
                },
                new TotemFunctions() {
                    @Override public void action(Player player) {
                        PotionEffect effect1 = new PotionEffect(
                                PotionEffectType.LEVITATION,3,14,true,true,true);
                        PotionEffect effect2 = new PotionEffect(
                                PotionEffectType.SLOW_FALLING,38,1,true,true,true);

                        player.addPotionEffect(effect1);
                        player.addPotionEffect(effect2);
                    }
                    @Override public void onHead(Player player) {

                    }
                }
        ));
        Totem.totemList.add(new Totem(
                "Вардено-отпугивательный тотем",
                List.of(
                        "При срабатывание: если сработает от вардена, то у вардена сброситься цель. А также игрок получит скорость-2, прыгучисть-1, невидимость, защиту от урона на 10 секунд."
                ),
                new Material[][]{
                        {null,Material.ECHO_SHARD,null},
                        {Material.ECHO_SHARD,Material.TOTEM_OF_UNDYING,Material.ECHO_SHARD},
                        {null,Material.ECHO_SHARD,null}
                },
                new TotemFunctions() {
                    @Override public void action(Player player) {

                        for (Entity entity : player.getWorld().getEntities()) {
                            if ( AddFunctions.getDistance( player.getLocation(),entity.getLocation() ) <= 80){
                                if (entity instanceof Warden){
                                    Warden warden = (Warden) entity;
                                    warden.setTarget(null);
                                }
                            }
                        }

                        PotionEffect effect1 = new PotionEffect(
                                PotionEffectType.SPEED,15,1,true,false,true);
                        PotionEffect effect2 = new PotionEffect(
                                PotionEffectType.JUMP,14,2,true,false,true);
                        PotionEffect effect3 = new PotionEffect(
                                PotionEffectType.INVISIBILITY,12,1,true,false,true);
                        PotionEffect effect4 = new PotionEffect(
                                PotionEffectType.DAMAGE_RESISTANCE,10,100,true,false,true);

                        player.addPotionEffect(effect1);
                        player.addPotionEffect(effect2);
                        player.addPotionEffect(effect3);
                        player.addPotionEffect(effect4);
                    }
                    @Override public void onHead(Player player) {

                    }
                }
        ));
        Totem.totemList.add(new Totem(
                "фаирбольный тотем",
                List.of(
                        "При срабатывание: от игрока в направление всех мобов/игроков запустится фаирбол, как у гаста.",
                        "При удержание: фаирболы гастов и эфритов будут отражаться."
                ),
                new Material[][]{
                        {null,null,null},
                        {Material.FIRE_CHARGE,Material.TOTEM_OF_UNDYING,Material.FIRE_CHARGE},
                        {null,null,null}
                },
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
                            if (entity instanceof Fireball) {
                                if (AddFunctions.getDistance(player.getLocation(), entity.getLocation()) < 10) {
                                    Fireball fireball = (Fireball) entity;
                                    Vector vector = fireball.getDirection();
                                }
                            }
                        }

                    }
                }
        ));
        Totem.totemList.add(new Totem(
                "",
                List.of(
                        "При срабатывание: ",
                        "При удержание: "
                ),
                new Material[][]{
                        {null,null,null},
                        {null,null,null},
                        {null,null,null}
                },
                new TotemFunctions() {
                    @Override public void action(Player player) {

                    }
                    @Override public void onHead(Player player) {

                    }
                }
        ));


    }*/
}
