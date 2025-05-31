# cpu3dengine:
A 3D rendering engine written in Java that runs on the CPU. Uses a left-handed coordinate system. Only tested on macOS.

## To run:
### In Terminal:
write 'java -cp bin Display'
A window showing a multicoloured, spinning monkey head should show up.

## Explanation:

A rendering engine is software that generates visual images from 3D data onto a 2D screen. This rendering engine consists of two parts: parsing a .obj file into faces and vertices to construct a 3D model, and rendering these faces using a depth-buffering method to ensure correct visibility and prevent overlap.

My implementation of a render engine is very simple, and lacks the majority of what makes functional rendering engines usable, such as textures(images) on the 3D model, lighting, a position system (models can only render at the centre), etc. Additionally it was built in order that I may understand the basic implementation of a render engine, and does not implement any optimisations and so is not very efficient. Additionally being a software renderer, it only runs on the CPU and on one thread.