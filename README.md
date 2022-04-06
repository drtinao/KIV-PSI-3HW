# KIV-PSI-3HW
Third HW assigned in KIV/PSI
1) Obsah repozitáře

Repozitář obsahuje ve složce "IntelliJ projekt" zdrojové kódy a celý projekt z IntelliJ IDEA. V rootu repozitáře se rovněž nachází soubor "rest_client.jar", který slouží pro uživatelsky přívětivé spuštění klienta.

2) Algoritmus použitý v programu

Pro zpracování tohoto úkolu nebylo potřeba využít žádný z obecně známých algoritmů. Bylo zapotřebí analyzovat obsah webových stránek, ze kterých byla data získávána a následně z nich vyparsovat pouze užitečné informace. Logiku parsování obsahuje třída "ParseLogic", jejíž metody jsou vybaveny JavaDocem.

Logiku vyhodnocování obsahuje třída "EvalLogic", která je rovněž komentována. Program uživateli sdělí, že se ISS nachází na aktuálně osvětlené straně Země za předpokladu, že UTC čas stanice se nachází v rozmezí, kdy na aktuální lokaci stanice dochází k východu / západu slunce - metoda "isInSunriseSunsetRange(LocalTime sunrise, LocalTime issTime, LocalTime sunset)".

Obdobně metoda "isGoodCondition(LocalTime sunrise, LocalTime issTime, LocalTime sunset)" vyhodnotí, zda je vhodný čas ke sledování ISS. Vhodný čas ke sledování je za předpokladu, že čas zbývající do východu slunce je v rozmezí 60 - 120 minut. Analogicky je stanici možno sledovat i pokud je mezi aktuálním časem a časem západu slunce rozdíl v rozmezí 60 - 120 minut.

3) Konstanty umožňující změnu chování programu

Níže uvedené konstanty definují stránky, ze kterých se získávají data potřebná pro vyhodnocení. Pokud bude některá z konstant změněna, bude potřeba aktualizovat třídu "ParseLogic". Uvedené samozřejmě platí za předpokladu, že nově zvolená stránka bude mít jinou strukturu než původní. Veškeré níže uvedené konstanty se nachází ve třídě "InternetLogic".

- ISS_NOW_LOC_API = adresa ISS Now API
- SUNRISE_SUNSET_API = adresa Sunrise/Sunset API
- UNIX_TIMESTAMP_API = adresa API použitého pro převod Unix timestamp

4) Spuštění aplikace

Ke spuštění je potřeba mít nainstalovanou Javu. Testování probíhalo na počítači s MS Windows 10 Pro, OpenJDK v11.0.12.

Pro spuštění se přesuňte do složky s programem a vykonejte příkaz "java -jar ./rest_client.jar".

5) Workflow programu

Po spuštění dojde ke stažení dat ze stránek ISS Now, tím získáme aktuální polohu ISS. Následně zjistíme, kdy v lokaci ISS dochází k východu / západu slunce. Na základě těchto informací proběhne vyhodnocení kýžených otázek, tedy:

- nachází se ISS na osvětlené straně Země?
- je vhodná doba ke sledování ISS?

Program během své práce vypisuje přehledná hlášení, tedy pokud uživatele zajímají pouze odpovědi na uvedené dvě otázky, může sledovat pouze konec výpisu aplikace.
