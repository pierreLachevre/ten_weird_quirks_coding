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
