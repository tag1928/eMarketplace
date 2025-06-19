form.addEventListener("submit", handleSubmit);

async function handleSubmit(event)
{
    event.preventDefault();

    const files = file.files;
    const formData = new FormData();

    for (let x of files)
    {
        formData.append("file", x);
    }

    const json =
    {
        name: product_name.value,
        price: price.value,
        description: description.value,
        author_username: localStorage.getItem("username")
    }

    formData.append("json", JSON.stringify(json));

    console.log(formData);

    const url = "/listing";
    const requestContent = {method: "POST", body: formData};

    const response = await fetch(url, requestContent);

    if (response.status === 400)
    {
        alert("Username, price and a photo are required");
        return;
    }

    alert("Successfully uploaded a listing");
}