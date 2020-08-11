#!/bin/bash

#convert everything you can to functions

echo "<<<<<<<<<<<<<<<<<<<<<<	WordsFinder	>>>>>>>>>>>>>>>>>>>>"

echo -e "\n		      Welocme $USER"


# ************************** To take the Source path **********************************************
echo -n -e "\nEnter the path of directory/file you want to look into: "
read directory

until [[ -f "$directory" || -d "$directory" ]] 					# To check if the directory is exist
do
    echo -n -e "\nThis directory/file not exist on your filesystem, try again: "
    read directory
done	



# ************************** To take the Destination path **********************************************
echo -n -e "\nEnter the path of the directory you want to copy the files into: "
read destination
 
until [[ -d "$destination" ]]                             			# To check if the destination is exist
do
    echo -n -e "\nThis directory/file not exist on your filesystem, try again: " 
    read destination
done	


# ************************** To take the words / phrases to search for **********************************
echo -n -e "\nEnter number of words/phrases you want to look for: "
read numberOfPhrases

until  [[ "$numberOfPhrases" =~ ^[0-9]+$ ]]                 			   # To check if the inuput is integer
    do
        echo -n -e "\nSorry integers only, Enter integer number please: "
	read numberOfPhrases
done

declare -a phrases

echo -e "\nEnter The Words/phrases each on line: "

for((i=1;i<=numberOfPhrases;i++)); do

	echo -n "#$i: "&& read line
	
	phrases+=( $line )
	
done

# ************************** To search into each file **********************************

#mapfile -t paths < <( find $directory -type f -exec file {} + | grep "ASCII\|PDF" | cut -d: -f1 )
mapfile -t paths < <( find $directory -type f -exec file {} + | grep "ASCII" | cut -d: -f1 )  # here we could add other file types a part from ASCII files

for path in "${paths[@]}"				# For each file, search into it about the set of words/phrases.
do	
	cnt=0;
	countAllWords=0
	for phrase in "${phrases[@]}"
	do		
		
		mapfile -t linesArray < <( grep -n "\b$phrase\b" $path | awk -F  ":" '{print $1}' )	 # To store the lines number that word/phrase found in.
	
		numberOfOccurance=$(grep -o -i "\b$phrase\b" $path | wc -l)                              # To store number of times that word/phrase exist.
		temp=$(wc --word $path)
		set -- $temp
		numberOfWords="$1"									 # To store number of word in the current file.
		fileName=$(basename $path)								 # Extract filename from the path
		countAllWords=$(($countAllWords + $numberOfOccurance))
		if [ $numberOfOccurance -gt 0 ]								 # If at least one of the words exist, then we copy the file to
		then 											 # destination, and Store the analysis
			if [ $cnt -eq 0 ] 
			then
				echo -e "\n-----------------------------------------------------------------------------" >> WordsFinderAnalysis.txt
				echo -e "				$fileName" >> WordsFinderAnalysis.txt	
 				cp --backup=existing --suffix=.orig -t $destination $path		 # To prevent file overwriting when copy files have the same name
				cnt=1;
			fi	
			printf "Data about \"$phrase\" word/phrase: \n\n" >> WordsFinderAnalysis.txt		
			echo -e "  Lines number that word/phrase occure in: ${linesArray[*]}" '\n'>> WordsFinderAnalysis.txt
			echo -n -e "  Number of occurance: $numberOfOccurance" >> WordsFinderAnalysis.txt "   , "	# store the data in WordsFinderAnalysis.txt
			echo -e "  Number of word in the file: $numberOfWords" '\n' >> WordsFinderAnalysis.txt

		fi
	done
	if [ $countAllWords -gt 0 ]
	then
		echo -e "  Number of occurance of all the words/phrases in this file:  $countAllWords \n" >> WordsFinderAnalysis.txt
	fi 
done
cp --backup=existing --suffix=.orig -t $destination WordsFinderAnalysis.txt		 
echo "Goodbye" >> WordsFinderAnalysis.txt
echo "Done!"
echo "Do you want to print the Analysis file(y|n)?"
read ans
if [[ $ans == "y" || $ans == "Y" ]]
then	
cat WordsFinderAnalysis.txt
fi
rm WordsFinderAnalysis.txt
echo "You will find the analysis file in "$destination.""

# **************************************************************************************





