load_listings_button.addEventListener("click", loadListings);

function addListingHTML(json)
{
    json.forEach(o => listings.innerHTML += `<p>${o.name}</p><p>${o.price}</p><p>${o.description}</p>`);
}

async function loadListings()
{
    const response = await fetch(`/listing/${page_number.value}/${sort_by.value}`);

    response.json().then(o => addListingHTML(o));
}