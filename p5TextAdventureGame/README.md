Currently noticed problems:

- When picking up several items of the same class, the amount of the item decreases in the place where it's picked up, but the player always has only 1 of the item.
- When combining two items, I didn't check if there are enouht left to check because the previous checking way was wrong and would cause the combination of phone and battery to return -1 phones.
- After combining food, the new food with name "a new kind of weird food" cannot be used. 

I'll revise these when have time.


The game has 2 ends. 
- BAD END: jump off the window.
- HAPPY END: turn on the torch during night in the wardrobe room.

Items:
- keys: use when in a place with locked doors. 
- fish: there are two kinds of fish, one is good and one is bad (+ or - HP).
- phone: use when combined with battery, the only use is to looked at the time.
- watch: default item, check time.
- torch: only to be used during the night.
- book: useless

#### Since the UI is to an amazing extent antihumanly ugly and complicated, I put a demo here.
#### But it seems that the picture file is too big so there are some problems uplaoding/loading it, so here is a map:
![alt text](https://github.com/ningkko/dataStructure/blob/master/p5TextAdventureGame/map.jpg)


![alt text](https://github.com/ningkko/dataStructure/blob/master/p5TextAdventureGame/demo.jpeg)
