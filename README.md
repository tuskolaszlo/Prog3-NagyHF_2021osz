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

A ’Stat’ menüpont alatt megjelenő ’Eredmények’ menüelemre kattintva megnyílik előttünk egy új ablak, amely megjelenít egy listát a korábbi játékok eredményeiről. Ezek az eredmények csak abban az esetben kerülnek mentésre, ha a játékot sikeresen végjátszák a játékosok (nem szakítják meg).
Az ’Eredmények’ ablak kinézete:
![image](https://user-images.githubusercontent.com/100286042/196640201-05b311c3-9858-4bca-879b-c298ebd0235e.png)
Ennek a megnyitásával a játék nem áll meg, és nem is záródik be. A folytatáshoz át kell kattintani a játék ablakába, vagy ezt az ablakot kell bezárni.

A játék első felében a korongokat kell felhelyezni a pályára. Ez úgy tehető meg, hogy rá kell kattintani egy korongra, majd a kiválasztott mezőre. Ha rákattintunk egy korongra, azt a program egy zöld vonallal jelzi a felhasználónak, illetve az utasításokat tartalmazó szövegdobozban is megjelenik.

Az alábbi képen látható erről illusztráció (a második fehér korong lett kiválasztva):
![image](https://user-images.githubusercontent.com/100286042/196640425-2c7e4000-456a-4c18-a75a-6200e36e7147.png)

Amíg ki van választva egy korong, addig minden próbálkozásunk (kattintásunk) arra a korongra vonatkozik. A kiválasztás megszüntetéséhez újra rá kell kattintani arra a korongra.

Az éppen aktuális játékos, csak a saját korongjaira kattinthat, az ellenfél korongjaira való kattintásnál nem történik semmi. Kivétel ez alól a malom kirakása utáni törlés.

A korongok felrakása során a kiválasztás sorrendje tetszőleges, azaz bármelyik korongot elhelyezhetjük.

![image](https://user-images.githubusercontent.com/100286042/196640583-6cb25506-f5f2-4333-8369-150334155259.png)

Fontos megemlíteni, hogy a lerakott korongokat a játéknak ebben a szakaszában a játékos már nem tudja módosítani.

Ha az egyik játékos malmot rak ki, azt a program az utasítások között jelzi:
![image](https://user-images.githubusercontent.com/100286042/196640711-fd96bf98-165c-4b4d-beb1-78bd1fa2237e.png)
Ilyenkor az aktuális játékosnak ki kell választania az ellenfél korongjai közül egyet, és kattintás után az a korong törlődik. Saját korongot ilyenkor nem tudunk kiválasztani (se törléshez, se léptetéshez).
Ha egy olyan korongot szeretnénk levenni, amely egy malom része, akkor azt a program jelzi, és nem engedi törölni. Ilyenkor egy másik korongot kell kiválasztanunk. Ha az ellenfél minden korongja malomban van, akkor a játék korong levétel nélkül folytatódik. A korong törlés után (vagy az ehhez tartozó folyamat után) a másik játékos következik.

A játék első szakasza akkor ér véget, ha a fekete játékos is felrakta az összes korongját. Innentől kezdődik a következő szakasz, amelyben a korongok léptetése a feladat:
![image](https://user-images.githubusercontent.com/100286042/196640935-80fb5dc3-ef8c-42de-a47e-64ea109b3fe5.png)

Kezdetben a játékosok egy kiválasztott koronggal csak egy szomszédos, szabad mezőre léphetnek. Ha egy játékosnak 3 darab korongja marad, akkor kezdhet „ugrálni”, azaz bármelyik másik szabad mezőre léptetheti a korongokat. Erről a program megjelenít egy üzenetet az utasításokat tartalmazó szövegdobozban:

![image](https://user-images.githubusercontent.com/100286042/196641104-03c0d4ec-4593-4d53-a172-649c4459a753.png)
A játék kétféle módon érhet véget:
- Az egyik játékosnak elfogynak a korongjai, azaz 2 darab marad
- Úgy sikerül elhelyezni a korongokat, hogy az egyik játékos minden korongja „beszorul”, azaz nem tud szabályosan lépni egy koronggal sem, mert nincs szabad szomszédos mezője egyiknek sem.

A játék vége, ha elfogytak a korongok:
![image](https://user-images.githubusercontent.com/100286042/196641362-744c7052-0f80-43f5-8b16-4c99cebf6c0f.png) 

A játék vége, amikor nincs több lépés:
![image](https://user-images.githubusercontent.com/100286042/196641465-51b9213c-2786-4087-8b8e-5e0d479ef0bb.png) 

A játék vége az utasításokat jelző szövegdobozban megjelenik, és kiírja az aktuális körnek a nyertesét. Ekkor már egyik koronggal sem lehet lépni sehova, és az időmérő is leáll. Az eredmények elmentődnek egy külső XML fájlba, amelyeket meg lehet tekinteni a ’Stat’ fül alatt lévő ’Eredmények’ menüpont indításával. A külső fájl lényege, hogy az eredmények később is elérhetőek legyenek, a program többszöri futtatása során is. A játék végeztével új játék indítható, a ’Menu’ menüpont alatt.
