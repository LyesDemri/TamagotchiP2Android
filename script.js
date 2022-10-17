function show_image(src, width, height, alt) {
 var img = document.createElement("img");
 img.src = src;
 img.width = width;
 img.height = height;
 img.alt = alt;

 // This next line will just add it to the <body> tag
 document.body.appendChild(img);
}

show_image("res/drawable/Egg_idle_1.png",
           80,80,
           "icone");

show_image("res/drawable/Egg_idle_2.png",
           80,80,
           "icone");