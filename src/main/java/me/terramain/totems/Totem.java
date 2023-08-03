package me.terramain.totems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Totem {
    public String name;
    public List<String> lore;
    public List<Craft> crafts;
    public TotemFunctions totemFunctions;


    public Totem(String name, List<String> lore){
        this.name = name;
        this.lore = lore;
        this.crafts = new ArrayList<>();
    }
    public Totem(String name, List<String> lore, TotemFunctions totemFunctions){
        this.name = name;
        this.lore = lore;
        this.totemFunctions = totemFunctions;
        this.crafts = new ArrayList<>();
    }
    public Totem(String name, List<String> lore, Craft craft){
        this.name = name;
        this.lore = lore;
        this.crafts = new ArrayList<>();
        this.crafts.add(craft);
    }
    public Totem(String name, List<String> lore, Craft craft, TotemFunctions totemFunctions) {
        this.name = name;
        this.lore = lore;
        this.crafts = new ArrayList<>();
        this.crafts.add(craft);
        this.totemFunctions = totemFunctions;
    }

    public ItemStack toItem(){
        ItemStack itemStack = new ItemStack(Material.TOTEM_OF_UNDYING);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Totem addCraft(Craft craft){
        this.crafts.add(craft);
        return this;
    }
    public Totem setFunctions(TotemFunctions totemFunctions){
        this.totemFunctions = totemFunctions;
        return this;
    }
    public Totem regAllCrafts(){
        for (Craft craft : this.crafts) {
            craft.generate(this.toItem());
        }
        return this;
    }




    public static void testsAllPlayers(){
        for (Player player : Bukkit.getOnlinePlayers()) {
            testPlayerTotems(player);
            customTotemsTests(player);
        }

    }
    public static void testPlayerTotems(Player player){
        boolean isActivate = activateTotem( player.getInventory().getItemInMainHand(), player, ActivationType.onHead);
        if (!isActivate) activateTotem( player.getInventory().getItemInOffHand(), player, ActivationType.onHead);
    }
    public static boolean activateTotem(ItemStack totem, Player player, ActivationType activationType){
        if (totem.getType() != Material.TOTEM_OF_UNDYING) return false;

        for (Totem totemOfFor : Totem.totemList) {
            if (
                    totem.getItemMeta().getDisplayName().equals( totemOfFor.name ) &&
                    totem.getItemMeta().getLore().equals( totemOfFor.lore )
            ){
                if (activationType==ActivationType.onAction) totemOfFor.totemFunctions.action(player);
                else if(activationType==ActivationType.onHead) totemOfFor.totemFunctions.onHead(player);
                else if(activationType==ActivationType.rightClick) totemOfFor.totemFunctions.rightClick(player);
                return true;
            }
        }
        return true;
    }
    public static void customTotemFunctionActivate(ItemStack totem, Player player, String arg, Object[] argsObjects){
        if (totem.getType() != Material.TOTEM_OF_UNDYING) return;

        for (Totem totemOfFor : Totem.totemList) {
            if (
                    totem.getItemMeta().getDisplayName().equals( totemOfFor.name ) &&
                            totem.getItemMeta().getLore().equals( totemOfFor.lore )
            ){
                totemOfFor.totemFunctions.custom1(player,arg,argsObjects);
                return;
            }
        }
    }
    public static void customTotemFunctionActivate(ItemStack totem, Player player, String arg){
        customTotemFunctionActivate(totem, player, arg,null);
    }

    public static void customTotemsTests(Player player){
        if ( !player.isHandRaised() && TotemData.holdShieldPlayers.contains(player)){
            if ( player.getInventory().getItemInMainHand().getType()==Material.SHIELD ){
                player.getInventory().setItemInMainHand(Totem.getTotemFromName("тотем щита").toItem());
                TotemData.holdShieldPlayers.remove(player);
            }
            else if ( player.getInventory().getItemInOffHand().getType()==Material.SHIELD ){
                player.getInventory().setItemInOffHand(Totem.getTotemFromName("тотем щита").toItem());
                TotemData.holdShieldPlayers.remove(player);
            }
        }
    }

    public static Totem getTotemFromName(String name){
        for (Totem totem : totemList) {
            if (totem.name.equals(name)) return totem;
        }
        return null;
    }


    public static List<Totem> totemList = new ArrayList<>();
}
interface TotemFunctions{
    void action(Player player);
    void onHead(Player player);
    void rightClick(Player player);
    void custom1(Player player,String arg,Object[] argsObjects);
}
class TotemData{
    public static List<UUID> deflectedFireballs = new ArrayList<>();
    public static List<Player> holdShieldPlayers = new ArrayList<>();
    public static List<Player> breakBlockWithMineTotemPlayers = new ArrayList<>();

}
enum ActivationType{onAction,onHead,rightClick}
