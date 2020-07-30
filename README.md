# AppCompatDialogFragment


### Introduction

this is a adapter for DialogFragment!

### Useage

1. Enable databinding

   ```groovy
   android {
       ...
           
       dataBinding {
           enabled = true
       }
   }
   ```

   

2. Config  gradle

   Add it in your root build.gradle at the end of repositories:

   ```groovy
   repositories {
       jcenter()
   }
   ```

   Add dependencies

   ```
   implementation 'com.murphy.appcompat:dialogfragment:1.0.0'
   ```

   

   library module  is full kotlin language, so it need add material dependencies when used that because if dont add this you can seeing the Kotlin issue  [KT-31052](https://youtrack.jetbrains.com/issue/KT-31052)

   ```
   implementation 'com.google.android.material:material:1.1.0'
   ```

   > **if you can fix it, commit issue!**

3. Use  AppCompatDialogFragmentAdapter

   Define your dialog fragment with you needed `bindLayout` and `initView`

   ```kotlin
   class NormalDialogFragment(marginRatio: Float) :
       AppCompatDialogFragmentAdapter<DialogFragmentNormalBinding>(marginRatio) {
   
       override fun initView(dataBinding: DialogFragmentNormalBinding) {
   
           dataBinding.button.setOnClickListener {
               dismiss()
           }
       }
   
       override fun bindLayout(): Int {
           return R.layout.dialog_fragment_normal
       }
   }
   ```

   Use

   ```kotlin
   val normalDialogFragment = NormalDialogFragment(0.05f)
   normalDialogFragment.isCancelable = false
   normalDialogFragment.show(supportFragmentManager, "normal")
   ```

   

4. Use  AppCompatBottomSheetDialogFragment

   Define your dialog fragment with you needed `bindLayout` and `initView`

   ```kotlin
   class NormalBottomSheetFragment :
       AppCompatBottomSheetDialogFragment<DialogFragmentBottomBinding>() {
   
       override fun initView(dataBinding: DialogFragmentBottomBinding) {
           dataBinding.button.setOnClickListener {
               dismiss()
           }
       }
   
       override fun bindLayout(): Int {
           return R.layout.dialog_fragment_bottom
       }
   }
   ```

   Use

   ```kotlin
   val normalBottomSheetFragment = NormalBottomSheetFragment()
   normalBottomSheetFragment.show(supportFragmentManager, "bottom")
   ```



### More 

- see code source!

