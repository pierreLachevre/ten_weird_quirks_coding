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

## PHP

## Python

## Ruby/ Ruby on Rails

## C#

## JavaScript

