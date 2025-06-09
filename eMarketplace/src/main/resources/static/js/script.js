async function loadListings()
{
    const pageNumber = document.getElementById("page_number").value - 1;
    const url = `http://localhost:8080/listing/${pageNumber}`;
    const response = await fetch(url);
    const json = response.json();

    json.then(arr =>
        {
            let string;
            arr.forEach(o => string +=
                `<div class="listing">
                <p>${o.name}</p>
                <p>${o.price}</p>
                </div>`);
            document.getElementById("listings").innerHTML = string;
        });
}