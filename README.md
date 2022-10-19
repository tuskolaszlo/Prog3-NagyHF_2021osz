# Prog3 NagyHF_2021osz
 BME-VIK A programozás alapjai 3 Nagy Házi Feladat 2021 őszi félévében

# Malom játék

A szoftver egy klasszikus malomjáték, amelyet két személy tud játszani egymás ellen.

## A játék leírása:

A malomjáték szabályai szerint egy fehér és egy fekete játékos van, akiknek 9-9 darab korongja van. Először ezeket a korongokat kell elhelyezni a pályán, majd felváltva léptetni kell a korongokat. Mindig a fehér játékos kezd. A játék során akkor alakul ki malom, ha 3 egymással szomszédos mezőn ugyanannak a játékosnak vannak korongjai. Ilyenkor a játékos le vehet az ellenfél korongjaiból egyet. Szabály szerint csak olyan korongot vehet le, amely nincs egy másik malomban. A játék akkor ér véget, ha valamelyik játékosnak 2 darab korongja maradt, vagy úgy sikerül elhelyezni a korongokat, hogy az egyik játékos már nem tud szabályosan lépni. Ha egy játékosnak 3 darab korongja marad, olyankor „ugrálhat” a korongokkal, azaz bármelyik mezőre léptetheti a korongjait.

## A program működése:

Indításnál megjelenik egy ablak, amelyben a játékosok nevei adhatók meg:
Itt a megfelelő szöveges mezőkben megadhatjuk a két játékos nevét.
A „Játék indítása” gombra kattintás után elindul a játék, ahol az itt megadott nevek fognak szerepelni.

![image](https://user-images.githubusercontent.com/100286042/196639452-0f2a3975-7a85-4b2a-aecc-8f0a42e2c798.png)

A játék felülete a következő képen látható:
![image](https://user-images.githubusercontent.com/100286042/196639669-615749cd-08c9-4412-9309-da87d64837ad.png)

A sötétszürke felületen látható a pálya és a korongok, ennek a jobb oldalán, a világos szürke felületen pedig a játék menetéhez szükséges feliratok, amelyek a játékosoknak nyújtanak segítséget. A szürke színű sávban megjelenő „FEHÉR” és „FEKETE” feliratok alatt szerepelnek a játék indításánál megadott játékos nevek (a megfelelő oszlopban). Ez alatt található egy szöveges mező, amelyet nem lehet szerkeszteni, de ez jeleníti meg az utasításokat, amelyek az adott játéksora vonatkoznak. Ez alatt található az időmérő felirat, amely mutatja mennyi idő telt el a játék indítása óta.

Az ablak felső sávjában található két menüpont. Az első menüpontban (’Menu’) két lehetőségünk van:
- Új játék: ezzel az aktuális játékot megszakítjuk, és visszatérünk ahhoz az ablakhoz, amelyet a program indításánál láttunk. Itt pedig egy teljesen új kört tudunk elkezdeni.
- Kilépés: ezzel a játék megszakításával kilépünk a programból (ugyanez elvégezhető a jobbfelső sarokban lévő ’X’-re kattintással).

