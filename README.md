# AVA22-java2-slutprojekt-robert-kloch

Production Regulator

Har försökt att använda mig av facade för att hantera GUIt men tror jag istället bara gjorde en vanlig controller.

Jag borde nog ha haft en gemensam superklass till både Worker och Consumer för de har likartade fält och metoder, men hann inte riktigt tänka ut något. Varje Worker och Consumer har en egen thread som tar/skapar ett Item utefter sitt intervall. För att lätt kunna komma åt alla så ligger de i en Queue. Detta innebär också att när du sparkar en Worker att det inte blir den sista som får sparken utan den som står först i kön, så du måste leva med de dåliga arbetarna ett tag.

Har en GUI-thread som uppdaterar färgerna och Progressbar. Behövde också två Threads i main som skötte uppdateringen av hur många items som finns och ändrar hur många konsumenter som finns i ett intervall.

Min Logg lägger senaste händelserna överst och loggar till pathen som bestäms i Main.

ItemBuffer är bara en buffer som har synchronized methods för att inte få problem med threads som arbetar (de undviker att de gör det samtidigt) på buffern. 
