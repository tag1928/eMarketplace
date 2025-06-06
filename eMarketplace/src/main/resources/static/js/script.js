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

async function uploadPosting()
{
    const url = "http://localhost:8080/listing";
    const productName = document.getElementById("product_name").value;
    const productDescription = document.getElementById("product_description").value;
    const requestBody =
        {
            name: productName,
            description: productDescription
        };

    const response = fetch(url, {method: POST, body: JSON.stringify(requestBody)});
    console.log(response);
}