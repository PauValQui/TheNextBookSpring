/*Funcion del menu desplegable*/

const btnMenu= document.getElementById("btnMenu")
const menu=document.getElementById("menu")
const spn1=document.getElementById("spn1")
const spn2=document.getElementById("spn2")
const spn3=document.getElementById("spn3")

function mostrar(){

    const height = 130;
    if(menu.classList.contains("desplegar")){
        menu.classList.remove("desplegar");
        menu.removeAttribute("style");
        
        spn1.style.transform = "rotate(0deg)";
        spn1.style.top = "0px";
        
        spn2.style.opacity = "1";
        
        spn3.style.transform = "rotate(0deg)";
        spn3.style.top = "22.5px";
        
    }else{
        menu.classList.add("desplegar");
        
        spn1.style.transform = "rotate(140deg)";
        spn1.style.top = "10px";
        
        spn2.style.opacity = "0";
        
        spn3.style.transform = "rotate(-140deg)";
        spn3.style.top = "10px";
        
        menu.style.height = height + "px";
    }

    menu.classList.toggle("mostrar");

}

btnMenu.addEventListener('click', mostrar);