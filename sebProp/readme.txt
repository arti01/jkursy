wywolanie:
java -jar sebProp.jar -opcja parametry

opcje: 
-f <plik properties z nowymi wartosciami> - podmienia wartości w plikach zdefiniowanych w config.properties, klucz plikiProperties
czyli bierze klucz i wartość z pliku podanego jako parametr wywolania (plik mapy), wyszukuje takie same klucze w plikach zdefiniowanych 
w konfiguracji aplikacji (config.properties, klucz plikiProperties) i podmienia ich wartość nową wartością.

np. plik mapy posiada klucz
IP_BM2=10.20.30.40
w config.properties zdefiniowane są dwa pliki, tuxedo.prop i bm2.prop, w których występuje klucz
IP_BM2=11.22.33.44
to po wykonaniu programu klucze w ww plikach przyjmą wartość
IP_BM2=10.20.30.40

Uwaga - w plikach app.prop i w plikach wzorcach konfiguracji aplikacji należy wyrównać nazwy kluczy
do kluczy używanych w pliku mapy.
Dla zmodyfikowanych plikow tworzona jest kopia ze znacznikiem czasu
===========================================================================================

-p <wzorzec cfg> <properties> <docelowy cfg>
Wykonanie tego, co wykonuje UCD podczas wdrożenia, czyli stworzenie na podstawie plikow
app.prop i plikow wzorcow konfiguracji docelowcyh plikow konfiguracyjnych
gdzie pierwszy parametr to wzorzec konfiguracji (plik z kluczami w formacie ${nazwa_klucza}), drugi to plik z properties (klucz=wartosc), trzeci to plik wynikowy (plik wzorca gdzie ${nazwa_klucza} jest zastąpiony przez wartosc)
np wzorzec domain="${BM2_DNS}", properties BM2_DNS=bm2zt7, wynikowy domain="bm2zt7"
Poprzednie pliki wynikowe są zabezpieczone przez wykonanie kopi ze znacznikiem czasu
Po zakończeniu parsowania system waliduje plik wynikowy sprawdzając, czy nie zostały w nim niezmienione klucze i jeśli coś takiego wystąpi zwraca je na wyjściu.
=============================================================================================

-z <plik mapy old> <plik mapy new>
porównuje pliki map, wyszukuje zmienione klucze i podmienia wartosci w plikach zdefiniowanych 
w (config.properties, klucz plikiMapOldNew) czyli:

stary plik mapy: IP_BM2=11.22.33.44
nowy plik mapy: IP_BM2=10.20.30.40
i plik konfiguracyjny, gdzie występuje fraza domainaddr=11.22.33.44
to po zmianie fraza będzie wygladać: domainaddr=10.20.30.40

Dla zmodyfikowanych plikow tworzona jest kopia ze znacznikiem czasu, wyrzuca też na wyjściu w jakim pliku co na co zmienil.
Uwaga - należy uzywać z rozsądkiem. Jesli np mamy nr portu 1010 i chcemy go zmienić na 10101, to przy ponowym uruchomieniu z tymi
samymi plikami map uzyskamy wynik 101011 (jedna jedynka więcej), gdyż z numeru 10101 fragment 1010 zostanie podmieniony na 10101.
Po prostu jedna zmiana i koniec. Nowa zmiana - nowe pliki map.