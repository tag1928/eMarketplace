document.addEventListener("DOMContentLoaded", function()
{
  const form = document.getElementById("create_listing");
  if (form)
  {
    form.addEventListener("submit", handleSubmit);
  }
  else
  {
    console.error("Form with ID 'create_listing' not found.");
  }
});

function handleSubmit(event)
{
  const form = event.currentTarget;
  const url = new URL(form.action);
  const productName = document.getElementById("product_name").value;
  const productDescription = document.getElementById("product_description").value;
  const formData = new FormData(form);
  formData.append(productName);
  formData.append(productDescription);
  const formDataObject = Object.fromEntries(formData.entries());

  const fetchOptions =
    {
      method: form.method,
      headers:
      {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formDataObject)
    };

  fetch(url, fetchOptions);
  event.preventDefault();
}