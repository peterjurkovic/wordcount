# wordcount

The word count program which ignore words which are partial matches of longer words. 

### Example

`A mate material may maybe right maybe`

**A** is discarded as it is contained in mate, material, may and maybe.

_**A** m**a**te m**a**teri**a**l m**a**y m**a**ybe right m**a**ybe_

**Mate** is discarded as it is contained in material.

_A mate **mate**rial may maybe right maybe_

**May** is discarded as it is contained in maybe

_A mate material may **may**be right **may**be_

##### Output
```
Material: 1
Maybe: 2
Right: 1
```


### Run

Download [wordcount.jar](https://github.com/peterjurkovic/wordcount/releases/download/1.0.0/wordcount.jar) and execute

`java -jar wordcount.jar file/path/to/text.txt`
