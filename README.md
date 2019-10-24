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

## Python

## Ruby/ Ruby on Rails

## C#

## JavaScript

