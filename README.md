# ten_weird_quirks_coding
V 1.0.0

Ce repository a éé créé afin de répertorier toutes les bizarreries pouvant être trouvées dans les langages de programmation.

Vous pouvez retrouver ici la liste de ces bizarreries par langage, une explication de chacune d'entre elles ainsi qu'une référence sur le fichier de code correspondant.

## Java
### BigCharacters.java
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

### Comment.java
```Java
public static void main(String[]args) {
         http://www.perdu.com
        System.out.println("Is this working?");
}
```
On peut constater une autre bizarrerie de Java. Toute chaîne de caractère déclaré hors d'une variable produit systématiquement une erreur de compilation. SAUF s'il s'agit d'une chaîne avec ```:```. Pourquoi? Et bien tout simplement que toute chaîne de caractère suivi d'un ```:``` est un label en Java. Un label est juste un moyen d'identifier un bloc en Java et permettent notamment de remplacer la méthode "goto" qui n'existe pas en Java. Ils sont de moins en moins utilisés cependant ils existent toujours. Exemple d'utilisation:

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

### WeirdVariables.java
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

### IntegerEquality.java

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

### Arithmetic.java

```Java
public static void main(String[] args) {
        char ch = '0';
        ch *= 1.1;
        System.out.println(ch);
}
```

Le résultat peut aussi étonner ici mais est tout à fait logique. Le code ASCII du caractère 0 est 48. Or lorsque on multiplie le caractère on multiplie en réalité sa valeur ASCII ce qui nous donne comme opération ```48 * 1.1 = 52.8```. Cette valeur est ensuite casté en caractère  (52) et lorsque l'on demande sa valeur à l'écriture on recherche alors le caractère correspondant au code ASCII 52 ==> 4.

## PHP

### WeirdIncrement.php

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

### WeirdComparator.php

```php
<?php     
    echo (int)(null > -1), "\n";
    echo (int)(null < 1), "\n";
    echo (int)(null == 0), "\n";
?>
```
Ici la logique est un peu complexe à comprendre mais peu être retrouvée dans la documentation avec un peu de recherche. Tout d'abord il faut comprendre la façon dont PHP interprète null lors d'une comparaison. D'après la documentation PHP consièdre que si la première opérande d'une opération est null et que la seconde est une valeur quelconque (peu importe le type sauf string) alors on considerera la comparation comme une comparaison entre 2 booléens : http://docs.php.net/manual/fr/language.operators.comparison.php

Ensuite il faut comprendre comment sont interprétés null, -1, 0 et 1 dans ces cas. D'après la documentation null et 0 sont considérés comme égaux à false et 1 et -1 sont considérés comme égaux à true.

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

### GreatCompare.php

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
Toute chaîne de caractère non vide est considéré comme un booléen lors d'une comparaison avec un booléen et comme un entier lors d'une comparaison avec un entier (si la chaîne ne peut être converti elle est alors égale à 0). De fait :

```echo "Rock the Goat" == true; ==> true donc 1```

```echo "Rock the Goat" == 0; ==> true donc 1```

```echo true == 0; ==> false donc 0```

Documentation: http://docs.php.net/manual/fr/language.operators.comparison.php
https://www.php.net/manual/fr/language.types.boolean.php

### WeirdLogic.php

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

### indent.py

```python
if True: 
print("without indent")

if True:
    print("with Indent")
```
Ici il ne s'agit pas vraiment d'une bizarrerie mais plus d'un point d'attention qui a pu poser des problèmes à de nombreux développeurs. En effet python ne possède pas de caractères permettant de délimitant des blocs de codes comme les accolades par exemple.
De fait python demande une extrème attention au niveau de l'indentation puisque l'oubli d'une indentation provoque une erreur.

### whatIsMyVariableValue.py

```python
a= True; b=1;
print a == b
print a+b
print str(a) == str(b)
```
Les résultats successifs de cet appel sont :

```
True
2
False
```
En fait le comportement des booléens est le même que pour Javascript, du coup le résultat est le plus logique car on consière true comme égal à 1. De fait les 2 premières instructions sont équivalentes à ```1==1 ==> true``` et ```1+1 ==> 2``` (ce qui est logique vu qu'en python bool est une sous classe de int).

### defaultVariable.py

```python
def append_ni(l=[]):
  l.append('ni!');
  return l;

print(append_ni())
print(append_ni(['nanan']))
print(append_ni())
```

Ici le résultat peut surprendre étant donné que dans beaucoup de langages l'initialisation par défaut d'un argument de méthode existe. De fait on pense que lorsque la méthode est appelée sans paramètre, un paramètre est créé à la volée et initialisé avec un tableau vide. Cependant le paramètre est ici initialisé lors de la définition de la méthode. Du coup le paramètre n'est initialisé qu'une fois et chaque appel de la méthode sans paramètre modifiera le paramètre par défaut.

### weirdArray.py

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

### compare.py

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

## Ruby/ Ruby on Rails

### variables.rb

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

De fait l'erreur présente sur a et b est normal. De même lorsque l'on fait ```a=b```, l'erreur est la même puisqu'on nous indique de b n'est pas définit. Toutefois pourquoi ne nous indique t-il pas que a n'est pas définit?

En fait l'explication se trouve dans l'interpréteur ruby. Celui-ci initialise toute les variables local avec nil quand il voit que l'on essaye de les assigner.
De fait dans ```a = b``` a est égal à nil mais b n'a pas de valeur. Donc on indique une erreur sur b. 

Dans l'instruction ```a=a ==> nil```, on retrouve la même chose, l'intepréteur initialise a avec nil avant de faire l'assignation ```a=a``` ce qui pourrait se traduire par ```a=nil``` ce qui donne ```nil```


### dates.rb

Attention ce code ci n'est utilisable qu'avec Rails car il utilise des helpers de Rails.

## C#

### CircleSquare.cs

```csharp
static void Main(string[] args)
{
    Square square1 = new Square(20, 30);
    Square square2 = square1;
    square2.X = 50;
    Console.WriteLine(square1.X);     
    Console.WriteLine(square2.X);     
}

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

Ici le résultat peut surprendre car on pense travailler sur les mêmes objets dans les deux cas avec le système de référence. Or Square et Circle sont déclarés de la façon suivante:

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

### Comparison.cs

```csharp
static void Main(string[] args)
{            
    string x = new string(new char[0]);
    string y = new string(new char[0]);
    Console.WriteLine(object.ReferenceEquals(x, y));
}
```

Ici on pourrait croire que l'on créé deux objets strings. Il s'agit cependant de deux variables créées à la compilation. De fait étant donné qu'elles ont la même valeur, les deux variables référencent le même objet et ont donc une référence égale.

### Inheritance.cs

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

Il s'agit plus d'une petite remarque concernant la syntaxe qu'une vraie bizarrerie, mais en C# l'héritage et l'implémentation utilise le même caractère ```:```. De fait il n'est pas possible de distinguer dans une classe les implémentations de l'héritage (au singulier car l'héritage multiple n'existe pas en C#), on peut seulement supposé que "peut-être" Base est une classe car l'héritage est forcément écrit avant toute implémentation.

## JavaScript

### addition.js

```javascript
[] + []

[] + {}

{} + []

{} + {}

[1, 2, 3] + [4, 5, 6];
[1, 2, 3] + [,4, 5, 6];

true + true

((true+true+true)*(true+false+true))*(Math.pow(true+true,(true+true))*(true+true)-true)
```

### array.js

```javascript
Array(25)

Array(25).join("nya")

Array(25).join("nya" + 1)

Array(25).join("nya" - 1)
```

### number.js

```javascript
Math.max() < Math.min() 

"foo" + +"bar"; 

NaN === NaN;

typeof NaN
```
