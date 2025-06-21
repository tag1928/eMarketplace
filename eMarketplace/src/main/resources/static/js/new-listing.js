submit_button.addEventListener("click", handleSubmit);

const url = "/listing";

async function handleSubmit()
{
    if (product_name.value.trim() === "")
    {
        alert("Product name is required");
        return;
    }

    if (price.value.trim() === "")
    {
        alert("Price is required");
        return;
    }

    const files = file.files;
    const formData = new FormData();

    for (let x of files)
    {
        formData.append("file", x);
    }

    const json =
    {
        name: product_name.value.trim(),
        price: price.value.trim(),
        description: description.value.trim(),
        author_username: localStorage.username
    }

    formData.append("json", JSON.stringify(json));

    const requestContent =
    {
        method: "POST",
        body: formData
    };

    const response = await fetch(url, requestContent);

    if (response.status === 400)
    {
        alert("Something went wrong");
        return;
    }

    alert("Successfully uploaded a listing");
}
