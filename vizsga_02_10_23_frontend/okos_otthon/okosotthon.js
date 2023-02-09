function panelek(db){
    const panelek=document.getElementById('panelek');
    const mSurfaceArea = db * 3.5;
    panelek.innerHTML="";
    for (i = 0; i < db; i++) {
        panelek.innerHTML += "<img src='napelem_ikon.png'>"; 
    }
    panelek.innerHTML += "<br>"; 
    panelek.innerHTML += "("+db+" db)";
    document.getElementById('osszteljesitmeny').value = db * 275;
    document.getElementById('tetofelulet').value = mSurfaceArea;
}

