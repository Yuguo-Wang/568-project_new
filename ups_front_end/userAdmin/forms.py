from django import forms
from django.utils import formats

class UserLoginForm(forms.Form):
    userid = forms.IntegerField()
    password = forms.CharField(widget = forms.PasswordInput)

class PackageSearchForm(forms.Form):
    userid = forms.CharField(max_length = 255)