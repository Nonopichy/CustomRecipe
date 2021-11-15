[![download](https://img.shields.io/badge/download-java-green)](https://github.com/Nonopichy/CustomRecipe/blob/main/CustomRecipe.java)
# CustomRecipe
* Version Support: **1.13.2** Below
* Frameworks: Bukkit, Lombok
* Async Event

### Craft with custom items
> <img src="img/gif2.gif" width="320" height="170">

### Only specific items to craft
> <img src="img/gif1.gif" width="320" height="170" >

# Usage:
### Slot: (MatrixItem 0-8)

<img src="img/matrix.png" width="320" height="170" >

### Code:
- To empty spaces, use ```null``` instead ```new ItemStack(Material.AIR)```
```java
CustomRecipe c = new CustomRecipe(this);
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
     },
result,false));
```

### Items used:

```java
// Item Result
ItemStack result = new ItemStack(Material.APPLE);
ItemMeta m = result.getItemMeta();
m.setDisplayName("&cMaça de Redstone");
result.addUnsafeEnchantment(Enchantment.LUCK,1);
m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
result.setItemMeta(m);

// Item Craft
ItemStack red = new ItemStack(Material.REDSTONE);
m = red.getItemMeta();
m.setDisplayName("&c&lRedstone");
red.addUnsafeEnchantment(Enchantment.LUCK,1);
m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
r.setItemMeta(m);
```

### Result:

<img src="https://user-images.githubusercontent.com/68911691/141708122-952daa07-f8dc-4b80-92e6-335b915587bc.png" width="520" height="240" >

