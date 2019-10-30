# ten_weird_quirks_coding
V 1.0.0

Ce repository a éé créé afin de répertorier toutes les bizarreries pouvant être trouvées dans les langages de programmation.

Vous pouvez retrouver ici la liste de ces bizarreries par langage, une explication de chacune d'entre elles ainsi qu'une référence sur le fichier de code correspondant.


## Documentation

* [Java](#java)
	- [Slang](#slang)
	- [Comment](#comment)
	- [WeirdVariables](#weirdvariables)
	- [IntegerEquality](#integerequality)
	- [Arithmetic](#arithmetic)
* [PHP](#php)
	- [WeirdIncrement](#weirdincrement)
	- [WeirdComparator](#weirdcomparator)
	- [GreatCompare](#greatcompare)
	- [WeirdLogic](#weirdlogic)
* [Python](#python)
	- [indent](#indent)
	- [whatIsMyVariableValue](#whatismyvariablevalue)
	- [weirdArray](#weirdarray)
	- [compare](#compare)
* [Ruby/Ruby on Rails](#ruby-ruby-on-rails)
	- [variables](#variables)
	- [dates](#dates)
* [C#](#csharp)
	- [CircleSquare](#circlesquare)
	- [Comparison](#comparison)
	- [Inheritance](#inheritance)
* [Javascript](#javascript)
	- [addition](#addition)
	- [array](#array)
	- [number](#number)
* [Liens utiles](#liens-utiles)


## Java
### Slang
```Java
private static void dohteMthgir(){
        for (char c‮ = 1; c‮ > 0; c‮++) {
            if (getNumericValue(c‮) > 1000) {
                System.out.println(c‮ + ": " + getNumericValue(c‮));
            }
        }
}
```
Ici on pourrait croire que certains caractères sont mal positionnés. Ce n'est pas du tout le cas. En fait un caractère est inséré en milieu de ligne qui permet d'écrire de droite à gauche. De plus ce caractère ```\u202e``` a une taille de 0 mais est parfaitement compris par Java.

Pour bien comprendre le fonctionnement il vous suffit de placer votre curseur en début de ligne sur la boucle ```for``` et de naviguer sur la droite.

### Comment
```Java
public static void main(String[]args) {
         http://www.perdu.com
        System.out.println("Is this working?");
}
```
On peut constater une autre bizarrerie de Java. Toute chaîne de caractère déclaré hors d'une variable produit systématiquement une erreur de compilation. SAUF s'il s'agit d'une chaîne avec ```:```. Pourquoi? Et bien tout simplement que toute chaîne de caractère suivie d'un ```:``` est un label en Java. Un label est juste un moyen d'identifier un bloc en Java et permettent notamment de remplacer la méthode "goto" qui n'existe pas en Java. Ils sont de moins en moins utilisés cependant ils existent toujours. Exemple d'utilisation:

```Java
greatExample:
while (Some condition)
{
  if ( a specific condition ) {
        break greatExample;   
  }
  else {
        ...
  }
}
```

### WeirdVariables
```Java
public static void main(String[] args) {
        String _‎ = "Hello ";
        String _‏ = "World";
        String _‎‏ = " !!";
        System.out.println(_‎+_‏+_‎‏);
}
```

Ce code peut vous faire lever un sourcil mais est tout à fait valable (du moins jusqu'en Java 9 où le _ est devenu un nom de variable protégé). En effet on a ici l'impression d'avoir 3 fois la même variable. Cependant Java accepte les caractères invisibles dans les noms de variables.
Pour comprendre le comportement vous pouvez exécuter le code suivant et récupérer le résultat puis l'exécuter.
```Java
public static void main(String[] args) {
    System.out.println("String _\u200e = \"Hello \";");
    System.out.println("String _\u200f = \"World\";");
    System.out.println("String _\u200e\u200f = \" !!\";");
    System.out.println("System.out.println(_\u200e+_\u200f+_\u200e\u200f);");
}
```

### IntegerEquality

```Java
    public static void main(String[] args) {
        firstEquality();
        secondEquality();
    }

    private static void firstEquality(){
        Integer a = 42;
        Integer b = 42;
        System.out.println(a == b);
    }
    
    private static void secondEquality(){
        Integer c = 666;
        Integer d = 666;
        System.out.println(c == d);
    }
```
Ici le résultat visualisabe est ```true``` puis ```false```. Pourquoi donc? Logiquement en Java lorsque l'on compare deux objets on compare leur référence. Or ici nous créons bien 2 objets à chaques fois de faire le résultat devrait être ```false``` et ```false```.

Cependant un cache est géré sur les objets ayant des valeurs entre -128 et 127 en Java. De fait quand une nouvelle variable est déclarée sur cette tranche de valeur alors elle partage la même référence que les autres sur cette même tranche.

### Arithmetic

```Java
public static void main(String[] args) {
        char ch = '0';
        ch *= 1.1;
        System.out.println(ch);
}
```

Le résultat peut aussi étonner ici mais est tout à fait logique. Le code ASCII du caractère 0 est 48. Or lorsque on multiplie le caractère on multiplie en réalité sa valeur ASCII ce qui nous donne comme opération ```48 * 1.1 = 52.8```. Cette valeur est ensuite casté en caractère  (52) et lorsque l'on demande sa valeur à l'écriture on recherche alors le caractère correspondant au code ASCII 52 ==> 4.

## PHP

### WeirdIncrement

```php
<?php
$x = null;
var_dump(--$x);
var_dump(++$x);
?>


```

Peu de chose à dire si ce n'est cité la documentation:
https://www.php.net/manual/fr/language.operators.increment.php

```
Note: Les opérateurs d'incrémentation/décrémentation n'affectent que les nombres et les chaînes de caractères. 
Les tableaux, objets, booléen et ressources ne sont pas affectés. 
La décrémentation des valeurs NULL n'a également aucun effet, mais leur incrémentation donnera comme résultat 1. 
```

### WeirdComparator

```php
<?php     
    echo (int)(null > -1), "\n";
    echo (int)(null < 1), "\n";
    echo (int)(null == 0), "\n";
?>
```
Ici la logique est un peu complexe à comprendre mais peu être retrouvée dans la documentation avec un peu de recherche. Tout d'abord il faut comprendre la façon dont PHP interprète ```null``` lors d'une comparaison. D'après la documentation PHP considère que si la première opérande d'une opération est null et que la seconde est une valeur quelconque (peu importe le type sauf string) alors on considérera la comparaison comme une comparaison entre 2 booléens : http://docs.php.net/manual/fr/language.operators.comparison.php

Ensuite il faut comprendre comment sont interprétés ```null```, ```-1```, ```0``` et ```1``` dans ces cas. D'après la documentation ```null``` et ```0``` sont considérés comme égaux à ```false``` et ```1``` et ```-1``` sont considérés comme égaux à ```true```.

De fait:

```
null > -1 ==> false > true ==> false et int(false) ==> 0
```

```
null < -1 ==> false < true ==> true et int(true) ==> 1
```

```
null == 0 ==> false == false ==> true et int(true) ==> 1
```

### GreatCompare

```php
<?php
echo "Rock the Goat" == true;
echo "\n";
echo "Rock the Goat" == 0;
echo "\n";
echo true == 0;
echo "\n";
?>
```

De la même façon que le cas précédent en lisant la documentation on comprend le résultat.
Toute chaîne de caractères non vide est considéré comme un booléen lors d'une comparaison avec un booléen et comme un entier lors d'une comparaison avec un entier (si la chaîne ne peut être converti elle est alors égale à ```0```). De fait :

```echo "Rock the Goat" == true; ==> true donc 1```

```echo "Rock the Goat" == 0; ==> true donc 1```

```echo true == 0; ==> false donc 0```

Documentation: http://docs.php.net/manual/fr/language.operators.comparison.php
https://www.php.net/manual/fr/language.types.boolean.php

### WeirdLogic

```php
<?php
$initial = 'R';
$name = (($initial == 'D') ? 'Dalc'
	: ($initial == 'R') ? 'Rock The Goat'
	: ($initial == 'S') ? 'Soplador'
	: ($initial == 'K') ? 'Kao'
	: 'unknown');
echo $name;
echo "\n";
?>
```

Ici on se retrouve avec un cas qui diffère de beaucoup de langages de programmation. En effet dans de nombreux langages le résultat serait "Rock The Goat" face à cette série d'opérateur ternaire.
En PHP toutefois le résultat est "Kao". Encore une fois la raison est expliquée dans la documentation: https://www.php.net/manual/fr/language.operators.comparison.php

On peut toutefois résumé la raison de la façon suivante:

```php
($initial == 'D') ? 'Dalc' ==> false
==> ($initial == 'R') ? 'Rock The Goat' ==> true
==> 'Rock The Goat' ? 'Soplador' ==> true
==> 'Soplador' ? 'Kao' ==> true
==> 'Kao' 
```
En effet en PHP les opérateurs ternaires en cascade sont évalués toujours de gauche à droite.

## Python

### indent

```python
if True: 
print("without indent")

if True:
    print("with Indent")
```
Ici il ne s'agit pas vraiment d'une bizarrerie mais plus d'un point d'attention qui a pu poser des problèmes à de nombreux développeurs. En effet python ne possède pas de caractères permettant de délimitant des blocs de codes comme les accolades par exemple.
De fait python demande une extrème attention au niveau de l'indentation puisque l'oubli d'une indentation provoque une erreur.

### whatIsMyVariableValue

```python
a= True; b=1;
print a == b
print a + b
print str(a) == str(b)
```
Les résultats successifs de cet appel sont :

```
True
2
False
```
En fait le comportement des booléens est le même que pour Javascript, du coup le résultat est le plus logique car on consière ```true``` comme égal à ```1```. De fait les 2 premières instructions sont équivalentes à ```1 == 1 ==> true``` et ```1 + 1 ==> 2``` (ce qui est logique vu qu'en python bool est une sous classe de int).

### defaultVariable

```python
def append_ni(l=[]):
  l.append('ni!');
  return l;

print(append_ni())
print(append_ni(['nanan']))
print(append_ni())
```

Ici le résultat peut surprendre étant donné que dans beaucoup de langages l'initialisation par défaut d'un argument de méthode existe. De fait on pense que lorsque la méthode est appelée sans paramètre, un paramètre est créé à la volée et initialisé avec un tableau vide. Cependant le paramètre est ici initialisé lors de la définition de la méthode. Du coup le paramètre n'est initialisé qu'une fois et chaque appel de la méthode sans paramètre modifiera le paramètre par défaut.

### weirdArray

```python
myArray = ['ni'] * 16
myArray[0] = 'We are the knights who say '
myArray[15] = 'We need a shrubbery !'
print myArray

myArray = [['ni'] * 16] * 3
print myArray

myArray[0][0]= 'We are the knights who say '
myArray[2][15]= 'We need a shrubbery !'
print myArray
```

En python il est tout à fait possible de créer un tableau à n dimensions grâce à l'opérateur de multiplication. Cependant le résultat du code précédent peut surprendre un peu mais est logique puisqu'avec l'utilisation de cet opérateur on ne créé pas trois tableaux mais un seul tableau que l'on référence trois fois.
De fait la modification d'une valeur du tableau entrainera la modification pour les 3 tableaux.

### compare

```python
print "" is ""
print 0 is 0
print [] is []
print {} is {}
```

L'opérateur ```is``` en python permet de vérifier si les deux objet comparés sont le même objet. Les 4 résultats précédents sont de fait logique.

```python
a= 256
b= 256
print a is b

a= 257
b= 257
print a is b
```

Toutefois quand on regarde les deux résultats précédents ceux-ci peuvent surprendre car on pourrait penser qu'il s'agit à chaque fois du même objet. Cependant en python si on créé un entier entre les valeurs -5 et 256 alors on référencera toujours le même objet.


https://docs.python.org/2/c-api/int.html

```
The current implementation keeps an array of integer objects for all integers between -5 and 256, when you create an int in that range you actually just get back a reference to the existing object. So it should be possible to change the value of 1. I suspect the behaviour of Python in this case is undefined. :-)
```

Toutefois si ```a != b``` lorsque a et b sont égaux à 257 alors comment expliquer le code suivant?

```python
a= 257; b= 257
print a is b
```

En fait quand la déclaration se fait sur la même ligne alors les variables référencent la même valeur.

## Ruby Ruby on Rails

### variables

```ruby
a
b
a = b
a = a 
```

Ici le résultat des différentes opérations peut paraitre étrange mais est tout à fait logique. Lorsque l'on essaye d'utiliser une variable non définie ruby nous en informe avec l'erreur

```
undefined local variable or method 'a' for main Object
```

De fait l'erreur présente sur ```a``` et ```b``` est normale. De même lorsque l'on fait ```a = b```, l'erreur est la même puisqu'on nous indique de b n'est pas définie. Toutefois pourquoi ne nous indique t-il pas que a n'est pas définie?

En fait l'explication se trouve dans l'interpréteur ruby. Celui-ci initialise toute les variables locales avec nil quand il voit que l'on essaye de les assigner.
De fait dans ```a = b``` ```a``` est égale à ```nil``` mais ```b``` n'a pas de valeur. Donc on indique une erreur sur ```b```. 

Dans l'instruction ```a = a ==> nil```, on retrouve la même chose, l'intepréteur initialise ```a``` avec ```nil``` avant de faire l'assignation ```a = a``` ce qui pourrait se traduire par ```a = nil``` ce qui donne ```nil```


### dates

Attention le code suivant n'est utilisable qu'avec Rails car il utilise des helpers de Rails.

```ruby
Date.today

"previous sunday".to_date

"next sunday".to_date
```

Dans le code précédent on a l'impression que lorsque l'on indique que l'on souhaite le ```previous sunday``` rails va automatiquement aller chercher le dimanche précédent. Il s'agit cependant d'une syntaxe trompeuse car il aurait été tout aussi possible d'écrire ```toto sunday``` ou ```est-ce que ça s'écrit sundae ou sunday ?``` au lieu de ```previous sunday```. En fait ruby et rails n'interprète que ce qu'ils peuvent comprendre et utiliser avec la méthode to_date.

Toutefois le fait que ce soit le dimanche précédent et non pas le dimanche suivant peut surprendre. En réalité si on considère la semaine comme un tableau, le premier indice de la semaine (indice 0) correspond à Dimanche, ce qui explique ce résultat (et pourquoi pour tous les autres jours de la semaine on indique bien le jour de la semaine courante).

## Csharp

### CircleSquare

```csharp
static void Main(string[] args)
{
    Square square1 = new Square(20, 30);
    Square square2 = square1;
    square2.X = 50;
    Console.WriteLine(square1.X);     
    Console.WriteLine(square2.X);     
}
```
```csharp
static void Main(string[] args)
{
    Circle circle1 = new Circle(Color.Black);
    Circle circle2 = circle1;
    circle2.color = Color.Blue;
    Console.WriteLine(circle1.color);     
    Console.WriteLine(circle2.color);     
}
```

Ici le résultat peut surprendre car on pense travailler sur les mêmes objets dans les deux cas avec le système de référence. Or ```Square``` et ```Circle``` sont déclarés de la façon suivante:

```csharp
public class Circle
{
	public Color color;
	public Circle(Color c){
	    color=c;
	}
}
```

```csharp
public struct Square
{
	public int X;
	public int Y;

	public Square(int x, int y){
	    X=x;
	    Y=y;
	}
}
```

En C# les structs sont des versions allégés des classes. Le struct est un type valeur et lorsque le struct est associé à une nouvelle variable alors le struct est copié dans la nouvelle variable.

https://docs.microsoft.com/fr-fr/dotnet/csharp/programming-guide/classes-and-structs/

### Comparison

```csharp
static void Main(string[] args)
{            
    string x = new string(new char[0]);
    string y = new string(new char[0]);
    Console.WriteLine(object.ReferenceEquals(x, y));
}
```

Ici on pourrait croire que l'on créé deux objets strings. Il s'agit cependant de deux variables créées à la compilation. De fait étant donné qu'elles ont la même valeur, les deux variables référencent le même objet et ont donc une référence égale.

### Inheritance

```csharp
public class Base
{
}

public interface Base2{

}

public class Derived : Base, Base2
{

}
```

Il s'agit plus d'une petite remarque concernant la syntaxe qu'une vraie bizarrerie, mais en C# l'héritage et l'implémentation utilise le même caractère ```:```. De fait il n'est pas possible de distinguer dans une classe les implémentations de l'héritage (au singulier car l'héritage multiple n'existe pas en C#), on peut seulement supposé que "peut-être" ```Base``` est une classe car l'héritage est forcément écrit avant toute implémentation.

## JavaScript

### addition

Lors d'une addition les deux opérandes sont converties en primitives avant de réaliser l'opération.
Convertir un objet en primitives retourne la valeur par défaut (pour un objet il s'agit de la méthode ```toString()``` qui est appelée).

```javascript
[] + []
```

Pour une tableau la méthode ```toString()``` correspond à l'appel à la méthode ```join()```. Ainsi l'appel à la méthode ```join()``` pour un tableau vide retourne une chaîne de caractère vide, d'où le résultat obtenu.

```javascript
[] + {}
```

Ici la méthode```toString()``` sur un objet non null et défini retourne ```[object Object]```

```javascript
{} + []
```

Ici la première opérande ```{}``` est considérée comme un bloc vide. La valeur retournée par un bloc vide est vide donc le résultat est équivalent à ```+[]```. L'opérande ```+``` convertit l'objet en primitive. De fait ```+[]``` correspond à ```ToNumber(ToPrimitive([]))```. Or la transformation primitive d'un tableau vide est une chaîne de caractères vide, et la transformation d'une chaîne vide en nombre retourne ```0```.

```javascript
{} + {}
```

On reprend ici le même raisonnement que précemment, sauf que dans ce cas on se retrouve avec ```+{}``` qui équivaut à ```ToNumber(ToPrimitive({}))```. La primitive d'un objet correspond à ```[object Object]``` ce qui équivaut à faire ```ToNumber([object Object])``` ce qui retourne ```NaN```.

```javascript
[1, 2, 3] + [4, 5, 6];
[1, 2, 3] + [,4, 5, 6];
```

Si on reprend l'explication précédente sur le passage en primitive d'un tableau, les résultats ici sont logiques (pour rappel, la transformation en primitive d'un objet consiste à appeler la méthode ```toString()``` de celui-ci, ce qui pour un tableau équivaut à appeler la méthode ```join()``` sur les arguments du tableau).

On se retrouve ainsi avec:

```javascript
"1, 2, 3" + "4, 5, 6";
"1, 2, 3" + ",4, 5, 6";
```

ce qui donne:

```javascript
"1, 2, 34, 5, 6";
"1, 2, 3,4, 5, 6";
```


```javascript
true + true
((true+true+true)*(true+false+true))*(Math.pow(true+true,(true+true))*(true+true)-true)
```

En Javascript ```true``` est égale à ```1``` et ```false``` à ```0``` de fait il est tout à fait possible de faire des opérations arithmétiques avec des booléens.

### array

```javascript
Array(25)

Array(25).join("nya")

Array(25).join("nya" + 1)
```
En Javascript lorsque l'on utilise l'opérateur ```+``` avec un chaîne et un entier on réalise un concaténation.

```javascript
Array(25).join("nya" - 1)
```

Cependant lorsque l'on utilise l'opérateur ```-```, on essaye de réaliser une opération arithmétique, donc on esaye de transformer la chaîne de caractère en entier dans un premier temps ce qui nous donne ```NaN```. 

### number

```javascript
Math.max() < Math.min() 
```

```Math.max``` renvoit le plus grand nombre comparé avec ```-Infini```. Et ```Math.min``` renvoit le plus petit nombre comparé à ```+Infini```.

Ainsi lorsque l'on fait ```Math.max(5)``` équivaut à faire ```Math.max(5, -Infini) ==> 5```
Et lorsque l'on fait ```Math.min(2)``` équivaut à faire ```Math.min(2, +Infini) ==> 2```

```javascript
"foo" + +"bar"; 
```

Le résultat peut encore une fois surprendre mais en fait l'opération précédente est interprétée comme ```'foo' + (+'bar')``` or ```(+'bar')``` est interprété comme NaN. Ainsi la concaténation suivante est faite ensuite ```'foo' + NaN```. D'où le résultat ```fooNan```

```javascript
typeof NaN
```

Il est important de préciser dans un premier temps que ```NaN``` n'est pas un mot clé comme ```true```, ```false``` etc, il s'agit en fait d'une propriété de l'objet global. En fait lorsque l'on affiche ```NaN``` on référence ```Number.NaN```. 
```NaN``` est de type ```Number``` comme définit dans la spécification EcmaScript: http://www.ecma-international.org/ecma-262/5.1/#sec-8.5

```javascript
NaN === NaN;
```

Le résultat ici est false tout simplement comme définit dans la spécification. Dans une comparaison ```===``` entre deux valeurs (exemple ```x === y```), on commence par comparer leur type. Si les types sont équivalents et que ```x``` est un nombre alors si ```x``` ou ```y``` est ```NaN``` alors le résultat sera ```false```.

https://www.ecma-international.org/ecma-262/#sec-strict-equality-comparison

## Liens utiles

https://github.com/denysdovhan/wtfjs#-is-equal-

https://eev.ee/blog/2012/04/09/php-a-fractal-of-bad-design/

https://www.destroyallsoftware.com/talks/wat

https://wiki.theory.org/index.php/YourLanguageSucks
