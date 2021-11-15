[![download](https://img.shields.io/badge/download-java-green)](https://github.com/Nonopichy/CustomRecipe/blob/main/CustomRecipe.java)
# CustomRecipe
* Version Support: **1.13.2** Below
* Frameworks: Bukkit, Lombok
* Async Event

### Craft with custom items
> <img src="img/gif2.gif" width="320" height="170">

### Only specific items to craft
> <img src="img/gif1.gif" width="320" height="170" >

# Tutorial:
## Lombok:
- Follow steps (plugin intellij): https://projectlombok.org/setup/intellij
- Add [Lombok.jar](https://projectlombok.org/downloads/lombok.jar) in artifacts how you add bukkit
## CustomRecipe:
- Add [CustomRecipe.jar](https://github.com/Nonopichy/CustomRecipe/releases/download/CustomRecipe/CustomRecipe.jar) in artifacts (how you add bukkit and lombok)
- Create a new instance of 'CustomRecipe' in your Main class
- Create a new instance of 'Recipe' add to 'CustomRecipe'
- Execute method 'addRecipe' with the arguments
- :) Finish! Example? BELOW!

### Example Short:

- To empty spaces, use ```null``` instead ```new CustomRecipe.MatrixItem(new ItemStack(Material.AIR),SLOT)```
```java
CustomRecipe c = new CustomRecipe(YourMainInstance);
Recipe r = new Recipe();
r.setLoose(false);
r.setResult(new ItemStack(Material.DIAMOND));
        
ItemStack REDSTONE = new ItemStack(Material.REDSTONE);
r.setRecipe(
     new MatrixItem(REDSTONE, 0),
     null,
     new MatrixItem(REDSTONE, 2),
     null,
     new MatrixItem(REDSTONE, 4)
     );
     
c.addRecipe("REDSTONE_TO_DIAMOND", r);
```

<img src="img/matrix_example.png" width="426" height="213" >

### Example "Bigger":

```java
CustomRecipe c = new CustomRecipe(YourMainInstance);
c.addRecipe("APPLE_REDSTONE",
     new CustomRecipe.Recipe(
          new CustomRecipe.MatrixItem[]{
               new CustomRecipe.MatrixItem(red,0),
               new CustomRecipe.MatrixItem(red,1),
               new CustomRecipe.MatrixItem(red,2),
               new CustomRecipe.MatrixItem(red,3),
               new CustomRecipe.MatrixItem(new ItemStack(Material.APPLE),4),
               new CustomRecipe.MatrixItem(red,5),
               new CustomRecipe.MatrixItem(red,6),
               new CustomRecipe.MatrixItem(red,7),
               new CustomRecipe.MatrixItem(red,8)
     }, result, false));
```

### Items used:

```java
// Item Result
ItemStack result = new ItemStack(Material.APPLE);
ItemMeta m = result.getItemMeta();
m.setDisplayName("§cMaça de Redstone");
result.addUnsafeEnchantment(Enchantment.LUCK,1);
m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
result.setItemMeta(m);

// Item Craft
ItemStack red = new ItemStack(Material.REDSTONE);
m = red.getItemMeta();
m.setDisplayName("§c§lRedstone");
red.addUnsafeEnchantment(Enchantment.LUCK,1);
m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
r.setItemMeta(m);
```

### Result:

<img src="https://user-images.githubusercontent.com/68911691/141708122-952daa07-f8dc-4b80-92e6-335b915587bc.png" width="520" height="240" >

